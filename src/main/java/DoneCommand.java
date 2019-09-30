import java.io.IOException;

public class DoneCommand extends Command {
    /**
     * handles user's request to mark a task as done.
     * @param tasks TaskList of Tasks.
     * @param input String of user input.
     * @param storage Storage object.
     * @param ui Ui object.
     * @return String representing Duke's response.
     * @throws IOException named file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be opened for any other reason.
     */
    String execute(TaskList tasks, String input, Storage storage, Ui ui) throws IOException, DukeException {
        String[] line = input.split(" ");
        if (line.length == 1) {
            throw new DukeException("Done should be of the format 'done <integer>'");
        }
        int index = Integer.parseInt(line[1]) - 1;
        if (index < 0 || index > tasks.size()) {
            throw new DukeException("Please give me a valid index to work with");
        }
        Task done = tasks.get(index);
        done.doTask();
        storage.rewriteFile(tasks);
        String command = "Wow you finally accomplished something!: ";
        command += "\n" + done.toString();
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
