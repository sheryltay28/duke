import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class addCommand extends Command {
    /**
     * returns a Calendar object using values from input string.
     * @param input string of user input.
     * @return Calendar object.
     */
    private Calendar deadlineConvertToCalendar(String input) {
        String findDate[] = input.split("/");
        int day = Integer.parseInt(findDate[0]);
        int month = Integer.parseInt(findDate[1]) - 1;
        String findTime[] = findDate[2].split(" ");
        int year = Integer.parseInt(findTime[0]);
        int hour = Integer.parseInt(findTime[1].substring(0, 2));
        int minute = Integer.parseInt(findTime[1].substring(2));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute);
        return calendar;
    }

    /**
     * returns an arraylist containing Calendar objects according to input string.
     * @param input user input.
     * @return an arraylist containing 2 Calendar objects representing to and from timings.
     */
    private ArrayList eventConvertToCalendar(String input) {
        ArrayList<Calendar> calendars = new ArrayList<>();
        String[] findTo = input.split("-");
        Calendar from = deadlineConvertToCalendar(findTo[0]);
        calendars.add(from);
        int toHour = Integer.parseInt(findTo[1].substring(0, 2));
        int toMin = Integer.parseInt(findTo[1].substring(2));
        Calendar to = Calendar.getInstance();
        to.set(Calendar.HOUR_OF_DAY, toHour);
        to.set(Calendar.MINUTE, toMin);
        calendars.add(to);
        return calendars;
    }

    /**
     * handles user's request to add a task to the list.
     * @param tasks TaskList containing tasks to add new tasks to.
     * @param input user input.
     * @param storage Storage object.
     * @throws DukeException if user input does not fit required input standard.
     * @throws IOException named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason
     */
    void execute(TaskList tasks, String input, Storage storage) throws DukeException, IOException {
        String[] line = input.split(" ");
        if (line[0].equals("todo")) {
            if (line.length == 1) {
                throw new DukeException();
            }
            String todoTask = line[1];
            for (int i = 2; i < line.length; i++) {
                todoTask += " " + line[i];
            }
            Todo newTodo = new Todo(todoTask);
            tasks.add(newTodo);
            storage.appendToFile(newTodo.toString());
            System.out.println("Got it. I've added this task:");
            System.out.println(newTodo);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else if (line[0].equals("deadline")) {
            if (line.length == 1) {
                throw new DukeException();
            }
            String findDeadline[] = input.split("/");
            String findTask[] = findDeadline[0].split(" ");
            String findDate[] = input.split("by ");
            Calendar calendar = deadlineConvertToCalendar(findDate[1]);
            String deadlineTask = findTask[1];
            for (int i = 2; i < findTask.length; i++) {
                deadlineTask += " " + findTask[i];
            }
            Deadline deadline = new Deadline(deadlineTask, calendar);
            tasks.add(deadline);
            storage.appendToFile(deadline.toString());
            System.out.println("Got it. I've added this task:");
            System.out.println(deadline);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else if (line[0].equals("event")) {
            if (line.length == 1) {
                throw new DukeException();
            }
            String findTime[] = input.split("/");
            String findDate[] = input.split("at ");
            ArrayList<Calendar> calendars = eventConvertToCalendar(findDate[1]);
            String findTask[] = findTime[0].split(" ");
            String eventTask = findTask[1];
            for (int i = 2; i < findTask.length; i++) {
                eventTask += " " + findTask[i];
            }
            Event event = new Event(eventTask, calendars.get(0), calendars.get(1));
            tasks.add(event);
            storage.appendToFile(event.toString());
            System.out.println("Got it. I've added this task:");
            System.out.println(event);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    /**
     * returns a boolean representing if this is an exit command,
     * @return boolean.
     */
    boolean isExit() {
        return false;
    }
}
