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
}
