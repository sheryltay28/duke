import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * adds a task to the TaskList.
     * @param task Task object.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * returns a task of that index.
     * @param index integer representing the index.
     * @return a Task object in the TaskList.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * returns an integer representing size of TaskList.
     * @return an integer which is the size.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * removes a Task from the TaskList
     * @param index index of Task which is to be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
    }
}
