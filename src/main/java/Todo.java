public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    /**
     * returns string representation of task object.
     * @return string.
     */
    @Override
    public String toString() {
        return "T" + super.toString();
    }
}
