import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

public class Kalendarz {
    private HashMap<Integer, ArrayList<Activity>> activityCalendar;
    public Kalendarz() {
        this(7);
    }

    public Kalendarz(int numberOfDays){
        this.activityCalendar = new HashMap<>();

        for (int i = 1; i <= numberOfDays; i++) {
            this.activityCalendar.put(i, new ArrayList<Activity>());
        }
    }

    public ArrayList<Activity> filterActivities(int day, Predicate<Activity> predicate) {
        ArrayList<Activity> tasks = activityCalendar.get(day);
        ArrayList<Activity> filteredTasks = new ArrayList<>();

        for (Activity task : tasks) {
            if (predicate.test(task))
                filteredTasks.add(task);
        }

        return filteredTasks;
    }

    public void addActivity(Spotkanie meeting, Integer day) {
        this.activityCalendar.get(day).add(meeting);
    }

    public void addActivity(Zadanie task, Integer day) {
        this.activityCalendar.get(day).add(task);
    }

    public void removeMeeting(Integer day, int meetingNumber) {
        int objNumber = 0;

        for (Activity a : this.activityCalendar.get(day)) {
            if (a instanceof Spotkanie && objNumber == meetingNumber) {
                this.activityCalendar.get(day).remove(objNumber);
                break;
            }
            if (a instanceof Spotkanie)
                objNumber++;
        }
    }

    public void removeTask(Integer day, int taskNumber) {
        int objNumber = 0;

        for (Activity a : this.activityCalendar.get(day)) {
            if (a instanceof Zadanie && objNumber == taskNumber) {
                this.activityCalendar.get(day).remove(objNumber);
                break;
            }
            if (a instanceof Zadanie)
                objNumber++;
        }
    }
}
