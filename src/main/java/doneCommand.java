import java.io.IOException;

public class doneCommand extends Command {
    void execute(TaskList tasks, String input, Storage storage) throws IOException {
        String[] line = input.split(" ");
        Task done = tasks.get(Integer.parseInt(line[1]) - 1);
        done.doTask();
        storage.rewriteFile(tasks);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(done);
    }

    boolean isExit() {
        return false;
    }
}
