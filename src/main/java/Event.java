import java.util.Calendar;

public class Event extends Task {
    Calendar from;
    Calendar to;

    /**
     * constructor for Event.
     * @param task String representing task to do.
     * @param from Calendar representing start time and date.
     * @param to Calendar representing end time and date.
     */
    public Event(String task, Calendar from, Calendar to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    /**
     * returns Calendar which represents start date and time.
     * @return Calendar object.
     */
    @Override
    public Calendar getCalendar() {
        return this.from;
    }

    /**
     * returns Calendar which represents end date and time.
     * @return Calendar object.
     */
    @Override
    public Calendar getTo() {
        return this.to;
    }

    /**
     * returns string representation of task object.
     * @return string.
     */
    @Override
    public String toString() {
        return "E" + super.toString() + " | " + from.get(Calendar.DAY_OF_MONTH) + " "
                + getMonth(from) + " " + from.get(Calendar.YEAR) + " "
                + from.get(Calendar.HOUR_OF_DAY)
                + String.format("%02d", from.get(Calendar.MINUTE)) + "-"
                + to.get(Calendar.HOUR_OF_DAY)
                + String.format("%02d", to.get(Calendar.MINUTE));
    }
}
