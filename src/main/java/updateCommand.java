import java.io.IOException;
import java.util.Calendar;

public class updateCommand extends Command {
    /**
     * handles user's request to update details of a Task.
     * @param tasks TaskList of Tasks.
     * @param input String of user input.
     * @param storage Storage object.
     * @param ui Ui object.
     * @return String representing Duke's response.
     * @throws DukeException
     * @throws IOException
     */
    String execute(TaskList tasks, String input, Storage storage, Ui ui) throws DukeException, IOException {
        String[] line = input.split(" ");
        int index = Integer.parseInt(line[1]) - 1;
        String inst = line[2];
        Task task = tasks.get(index);
        if (inst.equals("task")) {
            String newTask = line[3];
            task.changeTask(newTask);;
        } else if (inst.equals("date")) {
            String[] taskString = task.toString().split("\\|");
            if (taskString[0].equals("T")) {
                throw new DukeException();
            } else {
                String date = line[3];
                String[] findDate = date.split("/");
                int day = Integer.parseInt(findDate[0]);
                assert (day >= 1 && day <= 31) : "day should be between 1 and 31";
                int month = Integer.parseInt(findDate[1]) - 1;
                assert (month >= 1 && month <= 12) : "month should be between 1 and 12";
                int year = Integer.parseInt((findDate[2]));
                assert (year > 0) : "year should not be negative";
                Calendar calendar = task.getCalendar();
                calendar.set(Calendar.DAY_OF_MONTH, day);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.YEAR, year);
            }
        } else if (inst.equals("start")) {
            String[] taskString = task.toString().split("\\|");
            if (taskString[0].equals("T")) {
                throw new DukeException();
            } else {
                String time = line[3];
                int hour = Integer.parseInt(time.substring(0, 2));
                assert (hour > 0 && hour <= 24) : "hour should be between 1 and 24";
                int min = Integer.parseInt((time.substring(2)));
                assert (min > 0 && min <= 59) : "minute should be between 1 and 59";
                Calendar calendar = task.getCalendar();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, min);
            }
        } else if (inst.equals("end")) {
            String[] taskString = task.toString().split("\\|");
            if (taskString[0].equals("T") || taskString[0].equals("D")) {
                throw new DukeException();
            } else {
                String time = line[3];
                int hour = Integer.parseInt(time.substring(0, 2));
                assert (hour > 0 && hour <= 24) : "hour should be between 1 and 24";
                int min = Integer.parseInt((time.substring(2)));
                assert (min > 0 && min <= 59) : "minute should be between 1 and 59";
                Calendar calendar = task.getTo();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, min);
            }
        }
        String command = "Alright, I've updated your task accordingly.";
        command += "\n" + task.toString();
        return command;
    }

    /**
     * returns a boolean to determine if this command is an exit command.
     * @return a boolean.
     */
    boolean isExit() {
        return false;
    }
}