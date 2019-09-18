import java.io.IOException;

public class findCommand extends Command {
    String execute(TaskList tasks, String input, Storage storage, Ui ui) throws DukeException, IOException {
        String find = "Here are the matching tasks in your list:";
        String[] line = input.split(" ");
        String keyword = line[1];
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            if (curr.getTask().contains(keyword)) {
                find += "\n" + curr.toString();
            } else {
                continue;
            }
        }
        return find;
    }

    boolean isExit() {
        return false;
    }
}
