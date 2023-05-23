import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void printOptions() {
        System.out.println();
        System.out.println("1. Add a meeting for a given day.");
        System.out.println("2. Remove a meeting from a given day.");
        System.out.println("3. Show all meetings from a given day.");
        System.out.println("4. Show all meetings from a given day with given priority.");
        System.out.println("5. Exit the program.");
        System.out.print("Choose an option (1-5): ");
    }

    public static String pickADay(Scanner scanner, int numberOfDays) throws Exception {
        System.out.print("Pick a day (1 - " + numberOfDays + "): ");
        String day = scanner.nextLine();

        if (Integer.parseInt(day) < 0 || Integer.parseInt(day) > numberOfDays)
            throw new Exception("Invalid day. Possible options (1 - " + numberOfDays + ").");

        return day;
    }

    public static String getInput(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static int getOption(Scanner scanner) {
        int option;

        option = Integer.parseInt(scanner.nextLine());
        System.out.println();

        return option;
    }

    public static void printMeetingsFromADay(ArrayList<Spotkanie> meetings, int day) {
        System.out.println();
        for (int i = 0; i < meetings.size(); i++)
            System.out.println((i + 1) + ". " + meetings.get(i).toString());

        if (meetings.size() == 0) {
            System.out.println("No meetings for day " + day);
        }
    }

    public static void createMeeting(Kalendarz meetingCalendar, Scanner scanner, int numberOfDays) throws Exception {
        System.out.println("Adding a meeting...");
        LocalTime startTime, endTime;

        int hour = Integer.parseInt(getInput(scanner, "Meeting start hour: "));
        int minute = Integer.parseInt(getInput(scanner, "Meeting start minute: "));
        startTime = LocalTime.of(hour, minute);
        if (startTime.isBefore(Spotkanie.START_MEETINGS_FROM))
            throw new Exception("Meetings can start from " + Spotkanie.START_MEETINGS_FROM.toString() + ".");

        int duration = Integer.parseInt(getInput(scanner, "Meeting duration (in minutes): "));
        endTime = LocalTime.of(hour, minute).plusMinutes(duration);
        if (endTime.isBefore(Spotkanie.START_MEETINGS_FROM) || endTime.isBefore(startTime))
            throw new Exception("Meetings cannot end before " + Spotkanie.START_MEETINGS_FROM.toString() + " or "
                + startTime + ".");

        String p = getInput(scanner, "Meeting priority (low, medium, high): ").toLowerCase();
        String desc = getInput(scanner, "Meeting description: ");
        Priority priority = p.equals("high") ? Priority.HIGH : (p.equals("medium") ? Priority.MEDIUM : Priority.LOW);

        int day = Integer.parseInt(pickADay(scanner, numberOfDays));
        meetingCalendar.addMeeting(new Spotkanie(startTime, endTime, desc, priority), day);
        System.out.println("Meeting added on " + day);
    }

    public static void printMeetings(Kalendarz meetingCalendar, Scanner scanner, int numberOfDays) throws Exception {
        int day = Integer.parseInt(pickADay(scanner, numberOfDays));
        ArrayList<Spotkanie> meetings = meetingCalendar.getMeetingsFromADay(day);
        printMeetingsFromADay(meetings, day);
    }

    public static void printMeetingsWithGivenPriority(Kalendarz meetingCalendar, Scanner scanner, int numberOfDays) throws Exception {
        int day = Integer.parseInt(pickADay(scanner, numberOfDays));
        String p = getInput(scanner, "Choose priority (low, medium, high): ").toLowerCase();
        Priority priority = p.equals("high") ? Priority.HIGH : (p.equals("medium") ? Priority.MEDIUM : Priority.LOW);
        System.out.println("\nShowing meetings on " + day + ", with priority: " + priority);

        ArrayList<Spotkanie> meetings = meetingCalendar.getMeetingsFromADayWithGivenPriority(day, priority);
        printMeetingsFromADay(meetings, day);
    }

    public static void removeMeeting(Kalendarz meetingCalendar, Scanner scanner, int numberOfDays) throws Exception {
        int day = Integer.parseInt(pickADay(scanner, numberOfDays));
        ArrayList<Spotkanie> meetings = meetingCalendar.getMeetingsFromADay(day);
        printMeetingsFromADay(meetings, day);
        int meetingNumber = Integer.parseInt(getInput(scanner, "Pick a meeting number: "));
        meetingCalendar.removeMeetingFromADay(day, meetingNumber - 1);
        System.out.println("\nMeeting removed.");
    }

    public static boolean executeOptions(Kalendarz meetingCalendar, int option, Scanner scanner, int numberOfDays) throws Exception {
        switch (option) {
            case 1 -> createMeeting(meetingCalendar, scanner, numberOfDays);
            case 2 -> removeMeeting(meetingCalendar, scanner, numberOfDays);
            case 3 -> printMeetings(meetingCalendar, scanner, numberOfDays);
            case 4 -> printMeetingsWithGivenPriority(meetingCalendar, scanner, numberOfDays);
            case 5 -> {
                return false;
            }
            default -> System.out.println("Invalid option");
        }

        return true;
    }
    public static void main(String[] args) {
        System.out.println("***** Meeting calendar *****");
        int numberOfDays = 7;
        Kalendarz meetingCalendar = new Kalendarz(numberOfDays);

        Scanner scanner = new Scanner(System.in);
        int option;
        boolean isRunning = true;

        while (isRunning) {
            try {
                printOptions();
                option = getOption(scanner);
                isRunning = executeOptions(meetingCalendar, option, scanner, numberOfDays);

            } catch (Exception e) {
                System.out.println();
                System.out.println(e.getMessage());
            }
        }
    }
}