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
           } else if (line[0].equals("todo")) {
                String todoTask = line[1];
                for (int i = 2; i < line.length; i++) {
                    todoTask += " " + line[i];
                }
                Todo newTodo = new Todo(todoTask);
                inputs.add(newTodo);
                System.out.println("Got it. I've added this task:");
                System.out.println(newTodo);
                System.out.println("Now you have " + inputs.size() + " tasks in the list.");
            } else if (line[0].equals("deadline")) {
                String findDeadline[] = input.split("/");
                String by = findDeadline[1].substring(3);
                String findTask[] = findDeadline[0].split(" ");
                String deadlineTask = findTask[1];
                for (int i = 2; i <findTask.length; i++) {
                    deadlineTask += " " + findTask[i];
                }
                Deadline deadline = new Deadline(deadlineTask, by);
                inputs.add(deadline);
                System.out.println("Got it. I've added this task:");
                System.out.println(deadline);
                System.out.println("Now you have " + inputs.size() + " tasks in the list.");
            } else if (line[0].equals("event")) {
                String findTime[] = input.split("/");
                String at = findTime[1].substring(3);
                String findTask[] = findTime[0].split(" ");
                String eventTask = findTask[1];
                for (int i = 2; i < findTask.length; i++) {
                    eventTask += " " + findTask[i];
                }
                Event event = new Event(eventTask, at);
                inputs.add(event);
                System.out.println("Got it. I've added this task:");
                System.out.println(event);
                System.out.println("Now you have " + inputs.size() + " tasks in the list.");
            } else if (input.equals("bye")) {
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
        if (done) {
            return "[✓] " + task;
        } else {
            return "[✗] " + task;
        }
    }
}

class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    String by;

    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {
    String at;

    public Event(String task, String at) {
        super(task);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}