import java.io.IOException;
import java.util.Calendar;

public class UpdateCommand extends Command {
    Boolean isTodo;
    Boolean isDeadline;
    /**
     * handles user's request to update details of a Task.
     * @param tasks TaskList of Tasks.
     * @param input String of user input.
     * @param storage Storage object.
     * @param ui Ui object.
     * @return String representing Duke's response.
     * @throws DukeException if user input does not follow input format.
     */

    String execute(TaskList tasks, String input, Storage storage, Ui ui) throws DukeException, IOException {
        String[] line = input.split(" ");
        if (line.length != 4) {
            throw new DukeException("Update should be of the format 'update <integer> task/date/time/end <new>'");
        }
        int index = Integer.parseInt(line[1]) - 1;
        if (index < 0 || index > tasks.size()) {
            throw new DukeException("Please give me a valid index to work with");
        }
        String inst = line[2];
        Task task = tasks.get(index);
        if (inst.equals("task")) {
            String newTask = line[3];
            if (newTask.equals("")) {
                throw new DukeException("new task string should not be empty");
            }
            task.changeTask(newTask);
        } else if (inst.equals("date")) {
            String[] taskString = task.toString().split("\\|");
            if (taskString[0].equals("T")) {
                throw new DukeException("There's no date to change for a todo task.");
            } else {
                String date = line[3];
                String[] findDate = date.split("/");
                int day = Integer.parseInt(findDate[0]);
                if (day < 1 || day > 31) {
                    throw new DukeException("day should be between 1 and 31");
                }
                int month = Integer.parseInt(findDate[1]) - 1;
                if (month < 1 || month > 12) {
                    throw new DukeException("month should be between 1 and 12");
                }
                int year = Integer.parseInt((findDate[2]));
                if (year < 1) {
                    throw new DukeException("year should not be negative");
                }
                Calendar calendar = task.getCalendar();
                calendar.set(Calendar.DAY_OF_MONTH, day);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.YEAR, year);
            }
        } else if (inst.equals("time")) {
            String[] taskString = task.toString().split("\\|");
            isTodo = taskString[0].equals("T");
            if (isTodo) {
                throw new DukeException("There's no time to change for a todo task");
            } else {
                String time = line[3];
                int hour = Integer.parseInt(time.substring(0, 2));
                if (hour < 0 || hour > 24) {
                    throw new DukeException("hour should be between 0 and 24");
                }
                int min = Integer.parseInt((time.substring(2)));
                if (min < 0 || min > 59) {
                    throw new DukeException("minute should be between 0 and 59");
                }
                Calendar calendar = task.getCalendar();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, min);
            }
        } else if (inst.equals("end")) {
            String[] taskString = task.toString().split("\\|");
            isTodo = taskString[0].equals("T");
            isDeadline = taskString[0].equals("D");
            if (isTodo || isDeadline) {
                throw new DukeException("There's no end time to change for a todo/deadline task");
            } else {
                String time = line[3];
                int hour = Integer.parseInt(time.substring(0, 2));
                if (hour < 0 || hour > 24) {
                    throw new DukeException("hour should be between 0 and 24");
                }
                int min = Integer.parseInt((time.substring(2)));
                if (min < 0 || min > 59) {
                    throw new DukeException("minute should be between 0 and 59");
                }
                Calendar calendar = task.getTo();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, min);
            }
        }
        storage.rewriteFile(tasks);
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
