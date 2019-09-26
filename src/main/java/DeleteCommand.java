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
    String execute(TaskList tasks, String input, Storage storage, Ui ui) throws IOException {
        String[] line = input.split(" ");
        int index = Integer.parseInt(line[1]) - 1;
        assert (index >= 0) : "index of task should not be negative";
        String delete = "Noted. I've removed this task:";
        delete += "\n" + tasks.get(index).toString();
        tasks.remove(index);
        storage.rewriteFile(tasks);
        delete += "\n" + "Now you have " + tasks.size() + " tasks in the list";
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
