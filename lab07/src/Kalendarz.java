import java.util.ArrayList;
import java.util.HashMap;

public class Kalendarz {
    private final HashMap<Integer, ArrayList<Spotkanie>> calendar;

    public Kalendarz() {
        this(7);
    }

    public Kalendarz(int numberOfDays){
        this.calendar = new HashMap<>();

        for (int i = 1; i <= numberOfDays; i++) {
            this.calendar.put(i, new ArrayList<Spotkanie>());
        }
    }

    public void addMeeting(Spotkanie meeting, Integer day) {
        this.calendar.get(day).add(meeting);
    }

    public void removeMeetingFromADay(Integer day, int meetingNumber) {
        this.calendar.get(day).remove(meetingNumber);
    }

    public ArrayList<Spotkanie> getMeetingsFromADay(Integer day) {
        return this.calendar.get(day);
    }

    public ArrayList<Spotkanie> getMeetingsFromADayWithGivenPriority(Integer day, Priority priority) {
        ArrayList<Spotkanie> meetings = this.calendar.get(day);
        ArrayList<Spotkanie> result = new ArrayList<>();

        for (Spotkanie meeting : meetings) {
            if (meeting.getPriority() == priority)
                result.add(meeting);
        }

        return result;
    }
}
