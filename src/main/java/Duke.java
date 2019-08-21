import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       ArrayList<Task> inputs = new ArrayList<>();

       System.out.println("Hello! I'm Duke");
       System.out.println("What can I do for you?");

       while (sc.hasNextLine()) {
           String input = sc.nextLine();
           String[] line = input.split(" ");
           Task task = new Task(input);
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list");
                for (int i = 0; i < inputs.size(); i++) {
                   Task curr = inputs.get(i);
                   int num = i + 1;
                   System.out.println(num + ". " + curr);
                }
            } else if (line[0].equals("done")){
                Task done = inputs.get(Integer.parseInt(line[1]) - 1);
                done.doTask();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(done);


           } else if (!input.equals("bye")) {
                inputs.add(task);
                System.out.println("added: " + input);
            } else {
               break;
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
     }
}

class Task {
    String task;
    boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void doTask() {
        this.done = true;
    }

    @Override
    public String toString() {
        if (done == true) {
            return "[✓] " + task;
        } else {
            return "[✗] " + task;
        }
    }
}
