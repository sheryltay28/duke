import java.io.IOException;

public class DeleteCommand extends Command {
    /**
     * handles user's request to delete a task.
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
            throw new DukeException("Delete should be of the format 'delete <integer>'");
        }
        int index = Integer.parseInt(line[1]) - 1;
        if (index < 0 || index > tasks.size()) {
            throw new DukeException("Please give me a valid index to work with");
        }
        String delete = "I hope you are not just gonna pretend this task doesn't exist:";
        delete += "\n" + tasks.get(index).toString();
        tasks.remove(index);
        storage.rewriteFile(tasks);
        delete += "\n" + "Here's a reminder of your task in case you regret deleting it.";
        return delete;
    }

    /**
     * returns a boolean representing if this command is an exit command.
     * @return boolean.
     */
    boolean isExit() {
        return false;
    }
}
