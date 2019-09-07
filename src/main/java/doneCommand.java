import java.io.IOException;

public class doneCommand extends Command {
    /**
     * handles user's request to mark a task as done.
     * @param tasks TaskList of tasks.
     * @param input String of user input.
     * @param storage Storage object.
     * @throws IOException named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason.
     */
    void execute(TaskList tasks, String input, Storage storage) throws IOException {
        String[] line = input.split(" ");
        Task done = tasks.get(Integer.parseInt(line[1]) - 1);
        done.doTask();
        storage.rewriteFile(tasks);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(done);
    }

    /**
     * returns a boolean to determine if this command is an exit command.
     * @return a boolean.
     */
    boolean isExit() {
        return false;
    }
}
