import java.io.IOException;

public class deleteCommand extends Command {
    void execute(TaskList tasks, String input, Storage storage) throws IOException {
        String[] line = input.split(" ");
        int index = Integer.parseInt(line[1]) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index));
        tasks.remove(index);
        storage.rewriteFile(tasks);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    boolean isExit() {
        return false;
    }
}
