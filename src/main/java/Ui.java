import java.util.Calendar;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns a string which is the next line read by the scanner.
     * @return String which is next line of user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * prints error statement.
     * @param error String representing error message.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * prints welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void printTask(Task task) {
        System.out.println(task);
    }

    public void printListCommand(TaskList tasks) {
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            int num = i + 1;
            System.out.println(num + ". " + curr);
        }
    }

    public void printDeleteCommand() {
        System.out.println("Noted. I've removed this task:");
    }

    public void printSize(TaskList tasks) {
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
}

    public void printDoneCommand() {
        System.out.println("Nice! I've marked this task as done: ");
    }

    public void printExitCommand() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printFindCommand() {
        System.out.println("Here are the matching tasks in your list:");
    }

    public void printAddCommand() {
        System.out.println("Got it. I've added this task:");
    }
}
