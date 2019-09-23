import java.util.Calendar;

public class Task {
    String task;
    boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public Task() {

    }

    /**
     * returns a string which represents the task.
     * @return a String.
     */
    public String getTask() {
        return task;
    }

    /**
     * to set task as done.
     */
    public void doTask() {
        this.done = true;
    }

    /**
     * returns Calendar object of start date and time if applicable.
     * @return Calendar object.
     */
    public Calendar getCalendar() {
        return null;
    }

    /**
     * returns Calendar object of end date and time if applicable.
     * @return Calendar object.
     */
    public Calendar getTo() {
        return null;
    }

    /**
     * updates the task description to new task.
     * @param newTask
     */
    public void changeTask(String newTask) {
        this.task = newTask;
    }

    /**
     * returns a string which is the month corresponding to int of month in calendar.
     * @param c to get integer representing month.
     * @return the string which represents month.
     */
    public String getMonth(Calendar c) {
        if (c.get(Calendar.MONTH) == 0) {
            return "January";
        }
        else if (c.get(Calendar.MONTH) == 1) {
            return "February";
        }
        else if (c.get(Calendar.MONTH) == 2) {
            return "March";
        }
        else if (c.get(Calendar.MONTH) == 3) {
            return "April";
        }
        else if (c.get(Calendar.MONTH) == 4) {
            return "May";
        }
        else if (c.get(Calendar.MONTH) == 5) {
            return "June";
        }
        else if (c.get(Calendar.MONTH) == 6) {
            return "July";
        }
        else if (c.get(Calendar.MONTH) == 7) {
            return "August";
        }
        else if (c.get(Calendar.MONTH) == 8) {
            return "September";
        }
        else if (c.get(Calendar.MONTH) == 9) {
            return "October";
        }
        else if (c.get(Calendar.MONTH) == 10) {
            return "November";
        }
        else {
            return "December";
        }
    }

    /**
     * returns string representation of task object.
     * @return string.
     */
    @Override
    public String toString() {
        if (done) {
            return "|1| " + task;
        } else {
            return "|0| " + task;
        }
    }
}
