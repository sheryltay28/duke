import java.io.IOException;

public class findCommand extends Command {
    void execute(TaskList tasks, String input, Storage storage) throws DukeException, IOException {
        System.out.println("Here are the matching tasks in your list:");
        String[] line = input.split(" ");
        String keyword = line[1];
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            if (curr.getTask().contains(keyword)) {
                System.out.println(curr);
            } else {
                continue;
            }
        }
    }

    boolean isExit() {
        return false;
    }
}
