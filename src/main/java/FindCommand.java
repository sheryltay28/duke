import java.io.IOException;

public class FindCommand extends Command {
    /**
     * handles user's request to search for tasks based on a keyword.
     * @param tasks TaskList of Tasks.
     * @param input String of user input.
     * @param storage Storage object.
     * @param ui Ui object.
     * @return String representing Duke's response.
     */
    String execute(TaskList tasks, String input, Storage storage, Ui ui) {
        String find = "I made a list of tasks that match your keyword:";
        String[] line = input.split(" ");
        String keyword = line[1];
        assert (!keyword.equals("")) : "you shouldn't be wasting my time by finding nothing";
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
