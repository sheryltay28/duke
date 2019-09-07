public class listCommand extends Command {
    /**
     * executes user's request of displaying list.
     * @param tasks TaskList of Tasks.
     * @param input String of user input.
     * @param storage Storage object.
     */
    void execute(TaskList tasks, String input, Storage storage) {
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            int num = i + 1;
            System.out.println(num + ". " + curr);
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
