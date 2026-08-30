public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String description, String date) {
        super(description);
        this.dueDate = date;
        this.type = "D";
    }
}
