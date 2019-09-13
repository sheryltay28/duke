import java.util.Calendar;

public class Event extends Task {
    Calendar from;
    Calendar to;


    public Event(String task, Calendar from, Calendar to) {
        super(task);
        this.from = from;
        this.to = to;
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
