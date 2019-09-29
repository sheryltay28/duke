public class ListCommand extends Command {
    /**
     * executes user's request of displaying list.
     * @param tasks TaskList of Tasks.
     * @param input String of user input.
     * @param storage Storage object.
     * @param ui Ui object.
     * @return String representing Duke's response.
     */
    String execute(TaskList tasks, String input, Storage storage, Ui ui) {
        if (tasks.size() == 0) {
            return "Your list is empty, are you sure you are that free?";
        } else {
            String list = "Time to get pressured by the number of tasks in your list";
            for (int i = 0; i < tasks.size(); i++) {
                Task curr = tasks.get(i);
                int num = i + 1;
                list += "\n" + num + ". " + curr.toString();
            }
            return list;
        }
    }

    /**
     * returns a boolean to determine if this command is an exit command.
     * @return a boolean.
     */
    boolean isExit() {
        return false;
    }
}
