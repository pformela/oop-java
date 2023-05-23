import java.time.LocalTime;

final public class Zadanie extends Activity {

    private Status status;

    public static final LocalTime START_TASKS_FROM = LocalTime.of(8, 0);
    public Zadanie(LocalTime startTime, LocalTime endTime, String description, Status status) {
        super(startTime, endTime, description);
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String toString() {
        return "ZADANIE: Start: " + this.getStartTime() + ", End: " + this.getEndTime() + ", Status: " + status.toString() +
                "\t\nDesc: " + this.getDescription();
    }
}
