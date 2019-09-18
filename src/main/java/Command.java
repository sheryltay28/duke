import java.io.IOException;

abstract class Command {
    /**
     * abstract method to handle user input.
     * @param tasks TaskList of Tasks.
     * @param input String of user input.
     * @param storage Storage object.
     * @throws DukeException if user input does not follow input format.
     * @throws IOException named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason.
     */
    abstract String execute(TaskList tasks, String input, Storage storage, Ui ui) throws
            DukeException, IOException;

    /**
     * abstract method to determine if command is an exit command.
     * @return a boolean.
     */
    abstract boolean isExit();
}
