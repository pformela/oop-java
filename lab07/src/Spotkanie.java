import java.time.LocalTime;

public class Spotkanie {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private Priority priority;

    public static final LocalTime START_MEETINGS_FROM = LocalTime.of(9, 0);

    public Spotkanie(LocalTime startTime, LocalTime endTime, String description, Priority priority) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.priority = priority;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public String getDescription() {
        return this.description;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setStartTime(LocalTime newStartTime){
        this.startTime = newStartTime;
    }

    public void setEndTime(LocalTime newEndTime) {
        this.endTime = newEndTime;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setPriority(Priority newPriority) {
        this.priority = newPriority;
    }

    public String toString() {
        String p = priority == Priority.LOW ? "low" : (priority == Priority.MEDIUM ? "medium" : "high");
        return "Start: " + this.startTime + ", End: " + this.endTime + ", Priority: " + p +
                "\nDescription: " + this.description;
    }
}
