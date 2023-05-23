import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void printOptions() {
        System.out.println();
        System.out.println("1. Add a meeting for a given day.");
        System.out.println("2. Add a task for a given day.");
        System.out.println("3. Remove a meeting from a given day.");
        System.out.println("4. Remove a task from a given day.");
        System.out.println("5. Get meetings from a given day .");
        System.out.println("6. Get tasks from a given day .");
        System.out.println("7. Get meetings from a given day with given priority.");
        System.out.println("8. Get tasks from a given day with given status.");
        System.out.println("9. Get meetings from a given day with given priority that start at a given time.");
        System.out.println("10. Get tasks from a given day with given priority that end at a given time.");
        System.out.println("11. Exit the program.");
        System.out.print("Choose an option (1-11): ");
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

    public static void printActivitiesFromADay(ArrayList<Activity> activities, int day) {
        System.out.println();
        for (int i = 0; i < activities.size(); i++)
            System.out.println((i + 1) + ". " + activities.get(i).toString());

        if (activities.size() == 0) {
            System.out.println("No activities for day " + day);
        }
    }

    public static void createTask(Kalendarz meetingCalendar, Scanner scanner, int numberOfDays) throws Exception {
        System.out.println("Adding a task...");
        LocalTime startTime, endTime;

        int hour = Integer.parseInt(getInput(scanner, "Task start hour: "));
        int minute = Integer.parseInt(getInput(scanner, "Task start minute: "));
        startTime = LocalTime.of(hour, minute);
        if (startTime.isBefore(Zadanie.START_TASKS_FROM))
            throw new Exception("Tasks can start from " + Zadanie.START_TASKS_FROM.toString() + ".");

        int duration = Integer.parseInt(getInput(scanner, "Task duration (in minutes): "));
        endTime = LocalTime.of(hour, minute).plusMinutes(duration);
        if (endTime.isBefore(Zadanie.START_TASKS_FROM) || endTime.isBefore(startTime))
            throw new Exception("Tasks cannot end before " + Zadanie.START_TASKS_FROM.toString() + " or "
                    + startTime + ".");

        String s = getInput(scanner, "Task status (planned, confirmed, in_progress, done): ").toLowerCase();
        String desc = getInput(scanner, "Task description: ");
        Status status = s.equals("done") ? Status.DONE :
                (s.equals("in_progress") ? Status.IN_PROGRESS : (s.equals("confirmed") ? Status.CONFIRMED : Status.PLANNED));

        int day = Integer.parseInt(pickADay(scanner, numberOfDays));
        meetingCalendar.addActivity(new Zadanie(startTime, endTime, desc, status), day);
        System.out.println("Task added on " + day);
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
        meetingCalendar.addActivity(new Spotkanie(startTime, endTime, desc, priority), day);
        System.out.println("Meeting added on " + day);
    }

    public static void printMeetings(Kalendarz meetingCalendar, Scanner scanner, int numberOfDays) throws Exception {
        int day = Integer.parseInt(pickADay(scanner, numberOfDays));
        ArrayList<Activity> activities = meetingCalendar.filterActivities(day, meeting -> meeting instanceof Spotkanie);
        printActivitiesFromADay(activities, day);
    }

    public static void printTasks(Kalendarz meetingCalendar, Scanner scanner, int numberOfDays) throws Exception {
        int day = Integer.parseInt(pickADay(scanner, numberOfDays));
        ArrayList<Activity> activities = meetingCalendar.filterActivities(day, meeting -> meeting instanceof Zadanie);
        printActivitiesFromADay(activities, day);
    }

    public static void printMeetingsWithGivenPriority(Kalendarz meetingCalendar, Scanner scanner, int numberOfDays) throws Exception {
        int day = Integer.parseInt(pickADay(scanner, numberOfDays));
        String p = getInput(scanner, "Choose priority (low, medium, high): ").toLowerCase();
        Priority priority = p.equals("high") ? Priority.HIGH : (p.equals("medium") ? Priority.MEDIUM : Priority.LOW);
        System.out.println("\nShowing meetings on " + day + ", with priority: " + priority);

        ArrayList<Activity> meetings = meetingCalendar.filterActivities(day, meeting -> (meeting.getPriority() == priority));
        printActivitiesFromADay(meetings, day);
    }

    public static void printTasksWithGivenStatus(Kalendarz meetingCalendar, Scanner scanner, int numberOfDays) throws Exception {
        int day = Integer.parseInt(pickADay(scanner, numberOfDays));
        String s = getInput(scanner, "Choose task status (planned, confirmed, in_progress, done): ").toLowerCase();
        Status status = s.equals("done") ? Status.DONE :
                (s.equals("in_progress") ? Status.IN_PROGRESS : (s.equals("confirmed") ? Status.CONFIRMED : Status.PLANNED));
        System.out.println("\nShowing tasks on " + day + ", with status: " + status);

        ArrayList<Activity> meetings = meetingCalendar.filterActivities(day, meeting -> meeting instanceof Zadanie && (meeting.getStatus() == status));
        printActivitiesFromADay(meetings, day);
    }

    public static void printMeetingsFromStartTime(Kalendarz meetingCalendar, Scanner scanner, int numberOfDays) throws Exception {
        int day = Integer.parseInt(pickADay(scanner, numberOfDays));
        int hour = Integer.parseInt(getInput(scanner, "Enter start hour: "));
        int minute = Integer.parseInt(getInput(scanner, "Enter start minute: "));
        LocalTime startTime = LocalTime.of(hour, minute);
        System.out.println("\nShowing meetings on " + day + ", that start at: " + startTime.toString());

        ArrayList<Activity> meetings = meetingCalendar.filterActivities(day, meeting -> (meeting.getStartTime().isAfter(startTime)));
        printActivitiesFromADay(meetings, day);
    }

    public static void printMeetingsBetweenStartAndEndTime(Kalendarz meetingCalendar, Scanner scanner, int numberOfDays) throws Exception {
        int day = Integer.parseInt(pickADay(scanner, numberOfDays));
        int startHour = Integer.parseInt(getInput(scanner, "Enter start hour: "));
        int startMinute = Integer.parseInt(getInput(scanner, "Enter start minute: "));
        LocalTime startTime = LocalTime.of(startHour, startMinute);

        int endHour = Integer.parseInt(getInput(scanner, "Enter end hour: "));
        int endMinute = Integer.parseInt(getInput(scanner, "Enter end minute: "));
        LocalTime endTime = LocalTime.of(endHour, endMinute);

        System.out.println("\nShowing meetings on " + day + ", that start at: " + startTime.toString() + ", and end at: " + endTime.toString());

        ArrayList<Activity> meetings = meetingCalendar.filterActivities(day,
                meeting -> (meeting.getStartTime().isAfter(startTime)) && meeting.getEndTime().isBefore(endTime));
        printActivitiesFromADay(meetings, day);
    }

    public static void printMeetingsWithGivenPriorityAndFromStartTime(Kalendarz meetingCalendar, Scanner scanner, int numberOfDays) throws Exception {
        int day = Integer.parseInt(pickADay(scanner, numberOfDays));
        String p = getInput(scanner, "Choose priority (low, medium, high): ").toLowerCase();
        Priority priority = p.equals("high") ? Priority.HIGH : (p.equals("medium") ? Priority.MEDIUM : Priority.LOW);

        int startHour = Integer.parseInt(getInput(scanner, "Enter start hour: "));
        int startMinute = Integer.parseInt(getInput(scanner, "Enter start minute: "));
        LocalTime startTime = LocalTime.of(startHour, startMinute);

        System.out.println("\nShowing meetings on " + day + ", that start at: " + startTime.toString() + ", with priority: " + priority);

        ArrayList<Activity> meetings = meetingCalendar.filterActivities(day,
                meeting -> meeting instanceof Spotkanie && (meeting.getStartTime().isAfter(startTime)) && meeting.getPriority() == priority);
        printActivitiesFromADay(meetings, day);
    }

    public static void printTasksWithGivenStatusAndUpToEndTime(Kalendarz meetingCalendar, Scanner scanner, int numberOfDays) throws Exception {
        int day = Integer.parseInt(pickADay(scanner, numberOfDays));
        String s = getInput(scanner, "Choose task status (planned, confirmed, in_progress, done): ").toLowerCase();
        Status status = s.equals("done") ? Status.DONE :
                (s.equals("in_progress") ? Status.IN_PROGRESS : (s.equals("confirmed") ? Status.CONFIRMED : Status.PLANNED));

        int endHour = Integer.parseInt(getInput(scanner, "Enter end hour: "));
        int endMinute = Integer.parseInt(getInput(scanner, "Enter end minute: "));
        LocalTime endTime = LocalTime.of(endHour, endMinute);

        System.out.println("\nShowing tasks on day " + day + ", that end before: " + endTime.toString() + ", with status: " + status);

        ArrayList<Activity> meetings = meetingCalendar.filterActivities(day,
                meeting -> meeting instanceof Zadanie && (meeting.getEndTime().isBefore(endTime)) && meeting.getStatus() == status);
        printActivitiesFromADay(meetings, day);
    }

    public static void removeMeeting(Kalendarz meetingCalendar, Scanner scanner, int numberOfDays) throws Exception {
        int day = Integer.parseInt(pickADay(scanner, numberOfDays));
        ArrayList<Activity> meetings = meetingCalendar.filterActivities(day, meeting -> meeting instanceof Spotkanie);
        printActivitiesFromADay(meetings, day);
        int meetingNumber = Integer.parseInt(getInput(scanner, "Pick a meeting number: "));
        meetingCalendar.removeMeeting(day, meetingNumber - 1);
        System.out.println("\nMeeting removed.");
    }

    public static void removeTask(Kalendarz meetingCalendar, Scanner scanner, int numberOfDays) throws Exception {
        int day = Integer.parseInt(pickADay(scanner, numberOfDays));
        ArrayList<Activity> tasks = meetingCalendar.filterActivities(day, meeting -> meeting instanceof Zadanie);
        printActivitiesFromADay(tasks, day);
        int taskNumber = Integer.parseInt(getInput(scanner, "Pick a task number: "));
        meetingCalendar.removeTask(day, taskNumber - 1);
        System.out.println("\nTask removed.");
    }

    public static boolean executeOptions(Kalendarz meetingCalendar, int option, Scanner scanner, int numberOfDays) throws Exception {
        switch (option) {
            case 1 -> createMeeting(meetingCalendar, scanner, numberOfDays);
            case 2 -> createTask(meetingCalendar, scanner, numberOfDays);
            case 3 -> removeMeeting(meetingCalendar, scanner, numberOfDays);
            case 4 -> removeTask(meetingCalendar, scanner, numberOfDays);
            case 5 -> printMeetings(meetingCalendar, scanner, numberOfDays);
            case 6 -> printTasks(meetingCalendar, scanner, numberOfDays);
            case 7 -> printMeetingsWithGivenPriority(meetingCalendar, scanner, numberOfDays);
            case 8 -> printTasksWithGivenStatus(meetingCalendar, scanner, numberOfDays);
            case 9 -> printMeetingsWithGivenPriorityAndFromStartTime(meetingCalendar, scanner, numberOfDays);
            case 10 -> printTasksWithGivenStatusAndUpToEndTime(meetingCalendar, scanner, numberOfDays);
            case 11 -> {
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