public class Event extends Task{
    protected String startDate;
    protected String endDate;

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = "E";
    }

    public String toString() {
        return super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
