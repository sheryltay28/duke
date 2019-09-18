public class exitCommand  extends Command {
    /**
     * handles user's request to exit.
     * @param tasks TaskList of tasks.
     * @param input String of user input.
     * @param storage Storage object.
     */
    String execute(TaskList tasks, String input, Storage storage, Ui ui) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * returns a boolean representing if this command is an exit command.
     * @return a boolean.
     */
    boolean isExit() {
        return true;
    }
}
