import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       ArrayList<String> inputs = new ArrayList<>();

       System.out.println("Hello! I'm Duke");
       System.out.println("What can I do for you?");

       while (sc.hasNextLine()) {
           String input = sc.nextLine();
            if (input.equals("list")) {
               for (int i = 0; i < inputs.size(); i++) {
                   String curr = inputs.get(i);
                   int num = i + 1;
                   System.out.println(num + ". " + curr);
               }
           } else if (!input.equals("bye")) {
                inputs.add(input);
                System.out.println("added: " + input);
            } else {
               break;
           }
       }

       System.out.println("Bye. Hope to see you again soon!");
    }
}
