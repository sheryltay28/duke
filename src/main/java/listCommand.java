public class listCommand extends Command {
    void execute(TaskList tasks, String input, Storage storage) {
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            int num = i + 1;
            System.out.println(num + ". " + curr);
        }
    }

    boolean isExit() {
        return false;
    }
}
