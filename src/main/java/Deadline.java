import java.util.Calendar;

public class Deadline extends Task {
    Calendar by;

    public Deadline(String task, Calendar by) {
        super(task);
        this.by = by;
    }

    /**
     * returns a Calendar which represents date and time of deadline.
     * @return Calendar object.
     */
    @Override
    public Calendar getCalendar() {
        return this.by;
    }

    /**
     * returns string representation of task object.
     * @return string.
     */
    @Override
    public String toString() {
        return "D" + super.toString() + " | " + by.get(Calendar.DAY_OF_MONTH) + " "
                + getMonth(by) + " " + by.get(Calendar.YEAR) + " "
                + String.format("%02d", by.get(Calendar.HOUR_OF_DAY))
                + String.format("%02d", by.get(Calendar.MINUTE));
    }
}
