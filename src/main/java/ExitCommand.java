public class ExitCommand  extends Command {
    /**
     * handles user's request to exit.
     * @param tasks TaskList of Tasks.
     * @param input String of user input.
     * @param storage Storage object.
     * @param ui Ui object.
     * @return String representing Duke's reply.
     */
    String execute(TaskList tasks, String input, Storage storage, Ui ui) {
        return "Running away from your tasks so soon?";
    }

    /**
     * returns a boolean representing if this command is an exit command.
     * @return a boolean.
     */
    boolean isExit() {
        return true;
    }
}
