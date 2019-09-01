public class exitCommand  extends Command {
    void execute(TaskList tasks, String input, Storage storage) {
        System.out.println("Bye. Hope to see you again soon!");
    }

    boolean isExit() {
        return true;
    }
}
