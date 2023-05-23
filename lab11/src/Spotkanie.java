import java.time.LocalTime;

final public class Spotkanie extends Activity {

    private Priority priority;

    public static final LocalTime START_MEETINGS_FROM = LocalTime.of(9, 0);

    public Spotkanie(LocalTime startTime, LocalTime endTime, String description, Priority priority) {
        super(startTime, endTime, description);
        this.priority = priority;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority status) {
        this.priority = status;
    }

    public String toString() {
        return "SPOTKANIE: Start: " + this.getStartTime() + ", End: " + this.getEndTime() + ", Priority: " + priority.toString() +
                "\t\nDesc: " + this.getDescription();
    }
}
