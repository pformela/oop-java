import java.time.LocalTime;

sealed public abstract class Activity permits Spotkanie, Zadanie {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;

    public Activity(LocalTime startTime, LocalTime endTime, String description) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
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

    public Priority getPriority() { return null; }

    public Status getStatus() { return null; }

    public void setStartTime(LocalTime newStartTime){
        this.startTime = newStartTime;
    }

    public void setEndTime(LocalTime newEndTime) {
        this.endTime = newEndTime;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }


    public String toString() {
        return "ACTIVITY: Start: " + this.startTime + ", End: " + this.endTime + "\t\nDescription: " + this.description;
    }

}
