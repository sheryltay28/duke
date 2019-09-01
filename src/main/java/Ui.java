import java.util.Calendar;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public boolean hasNextLine() {
        return sc.hasNextLine();
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
}
