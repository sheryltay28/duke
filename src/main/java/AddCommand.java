import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class AddCommand extends Command {
    /**
     * returns a Calendar object using values from input string.
     * @param input string of user input.
     * @return Calendar object.
     */
    private Calendar deadlineConvertToCalendar(String input) throws DukeException {
        String[] findDate = input.split("/");
        int day = Integer.parseInt(findDate[0]);
        if (day < 1 || day > 31) {
            throw new DukeException("day should be between 1 and 31");
        }
        int month = Integer.parseInt(findDate[1]) - 1;
        if (month < 1 || month > 12) {
            throw new DukeException("month should be between 1 and 12");
        }
        String[] findTime = findDate[2].split(" ");
        int year = Integer.parseInt(findTime[0]);
        if (year < 1) {
            throw new DukeException("year should not be negative");
        }
        int hour = Integer.parseInt(findTime[1].substring(0, 2));
        if (hour < 0 || hour > 24) {
            throw new DukeException("hour should be between 0 and 24");
        }
        int minute = Integer.parseInt(findTime[1].substring(2));
        if (minute < 0 || minute > 59) {
            throw new DukeException("minute should be between 0 and 59");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute);
        return calendar;
    }

    /**
     * returns an arraylist containing Calendar objects according to input string.
     * @param input user input.
     * @return an arraylist containing 2 Calendar objects representing to and from timings.
     */
    private ArrayList<Calendar> eventConvertToCalendar(String input) throws DukeException {
        ArrayList<Calendar> calendars = new ArrayList<>();
        String[] findTo = input.split("-");
        Calendar from = deadlineConvertToCalendar(findTo[0]);
        calendars.add(from);
        int toHour = Integer.parseInt(findTo[1].substring(0, 2));
        int toMin = Integer.parseInt(findTo[1].substring(2));
        String[] startTime = findTo[0].split(" ");
        if (Integer.parseInt(startTime[1]) >= Integer.parseInt(findTo[1])) {
            throw new DukeException("You can't end before you start, please check properly before typing...");
        }
        Calendar to = Calendar.getInstance();
        to.set(Calendar.HOUR_OF_DAY, toHour);
        to.set(Calendar.MINUTE, toMin);
        calendars.add(to);
        return calendars;
    }

    /**
     * handles user's request to add a task to the list.
     * @param tasks TaskList of Tasks.
     * @param input String of user input.
     * @param storage Storage object.
     * @param ui Ui object.
     * @return String representing Duke's reply.
     * @throws DukeException if user input does not follow input format.
     * @throws IOException named file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be opened for any other reason.
     */
    String execute(TaskList tasks, String input, Storage storage, Ui ui) throws DukeException, IOException {
        String[] line = input.split(" ");
        Task task = new Task();
        if (line[0].equals("todo")) {
            if (line.length == 1) {
                throw new DukeException("Todo task should be of the format 'todo <task description>'");
            }
            String todoTask = line[1];
            for (int i = 2; i < line.length; i++) {
                todoTask += " " + line[i];
            }
            task = new Todo(todoTask);
        } else if (line[0].equals("deadline")) {
            if (line.length == 1) {
                throw new DukeException("Deadline task should be of the format 'deadline <task description> "
                        + "/by dd/mm/yyyy hhmm'");
            }
            String[] findDeadline = input.split("/");
            String[] findTask = findDeadline[0].split(" ");
            String[] findDate = input.split("/by ");
            Calendar calendar = deadlineConvertToCalendar(findDate[1]);
            String deadlineTask = findTask[1];
            for (int i = 2; i < findTask.length; i++) {
                deadlineTask += " " + findTask[i];
            }
            task = new Deadline(deadlineTask, calendar);
        } else if (line[0].equals("event")) {
            if (line.length == 1) {
                throw new DukeException("Event task should be of the format 'event <task description> "
                        + "/at dd/mm/yyyy hhmm-hhmm'");
            }
            String[] findTime = input.split("/");
            String[] findDate = input.split("/at ");
            ArrayList<Calendar> calendars = eventConvertToCalendar(findDate[1]);
            String[] findTask = findTime[0].split(" ");
            String eventTask = findTask[1];
            for (int i = 2; i < findTask.length; i++) {
                eventTask += " " + findTask[i];
            }
            task = new Event(eventTask, calendars.get(0), calendars.get(1));
        }
        tasks.add(task);
        storage.appendToFile(task.toString());
        String add = "Fine I've taken my precious time to add this task:";
        add += "\n" + task.toString();
        add += "\n" + "Now you have " + tasks.size() + " tasks in the list, time to start working!";
        return add;
    }

    /**
     * returns a boolean representing if this is an exit command.
     * @return boolean.
     */
    boolean isExit() {
        return false;
    }
}
