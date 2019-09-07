import java.io.IOException;

public class deleteCommand extends Command {
    /**
     * handles user's request to delete a task.
     * @param tasks TaskList of tasks.
     * @param input String of user's input.
     * @param storage Storage object.
     * @throws IOException named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason
     */
    void execute(TaskList tasks, String input, Storage storage) throws IOException {
        String[] line = input.split(" ");
        int index = Integer.parseInt(line[1]) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index));
        tasks.remove(index);
        storage.rewriteFile(tasks);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    /**
     * returns a boolean representing if this command is an exit command.
     * @return boolean.
     */
    boolean isExit() {
        return false;
    }
}
