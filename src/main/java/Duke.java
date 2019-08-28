import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }

    private static void rewriteFile(ArrayList list, String filePath) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < list.size(); i++) {
            String text = list.get(i).toString();
            fw.write(text + "\n");
        }
        fw.close();
    }

    public static void main(String[] args) throws EmptyDescriptionException, WrongInputException, FileNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> inputs = new ArrayList<>();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        try {
            String filePath = "/Users/sheryl/CS2103/duke/src/main/java/Duke.txt";
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);
            while(fileScanner.hasNextLine()) {
                //System.out.println("omg");
                String line = fileScanner.nextLine();
                //System.out.println("omg");
                String[] words = line.split("|");
                //System.out.println("or here");
                if (words[0].equals("T")) {
                    //System.out.println("Is it here");
                    Todo task = new Todo(words[2]);
                    inputs.add(task);
                }
                else if (words[0].equals("D")) {
                    Deadline task = new Deadline(words[2], words[3]);
                    inputs.add(task);
                }
                else if (words[0].equals("E")) {
                    Event task = new Event(words[2], words[3]);
                    inputs.add(task);
                }
            }
            //System.out.print("HELLO");

            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] line = input.split(" ");
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list");
                    for (int i = 0; i < inputs.size(); i++) {
                        Task curr = inputs.get(i);
                        int num = i + 1;
                        System.out.println(num + ". " + curr);
                    }
                } else if (line[0].equals("done")) {
                    Task done = inputs.get(Integer.parseInt(line[1]) - 1);
                    done.doTask();
                    rewriteFile(inputs, filePath);
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(done);
                } else if (line[0].equals("todo")) {
                    if (line.length == 1) {
                        throw new EmptyDescriptionException();
                    }
                    String todoTask = line[1];
                    for (int i = 2; i < line.length; i++) {
                        todoTask += " " + line[i];
                    }
                    Todo newTodo = new Todo(todoTask);
                    inputs.add(newTodo);
                    appendToFile(filePath, newTodo.toString());
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTodo);
                    System.out.println("Now you have " + inputs.size() + " tasks in the list.");
                } else if (line[0].equals("deadline")) {
                    if (line.length == 1) {
                        throw new EmptyDescriptionException();
                    }
                    String findDeadline[] = input.split("/");
                    String by = findDeadline[1].substring(3);
                    String findTask[] = findDeadline[0].split(" ");
                    String deadlineTask = findTask[1];
                    for (int i = 2; i < findTask.length; i++) {
                        deadlineTask += " " + findTask[i];
                    }
                    Deadline deadline = new Deadline(deadlineTask, by);
                    inputs.add(deadline);
                    appendToFile(filePath, deadline.toString());
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline);
                    System.out.println("Now you have " + inputs.size() + " tasks in the list.");
                } else if (line[0].equals("event")) {
                    if (line.length == 1) {
                        throw new EmptyDescriptionException();
                    }
                    String findTime[] = input.split("/");
                    String at = findTime[1].substring(3);
                    String findTask[] = findTime[0].split(" ");
                    String eventTask = findTask[1];
                    for (int i = 2; i < findTask.length; i++) {
                        eventTask += " " + findTask[i];
                    }
                    Event event = new Event(eventTask, at);
                    inputs.add(event);
                    appendToFile(filePath, event.toString());
                    System.out.println("Got it. I've added this task:");
                    System.out.println(event);
                    System.out.println("Now you have " + inputs.size() + " tasks in the list.");
                } else if (line[0].equals("delete")) {
                    int index = Integer.parseInt(line[1]) - 1;
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(inputs.get(index));
                    inputs.remove(index);
                    rewriteFile(inputs, filePath);
                    System.out.println("Now you have " + inputs.size() + " tasks in the list");
                }
                else if (input.equals("bye")) {
                    break;
                } else {
                    throw new WrongInputException();
                }
            }

            System.out.println("Bye. Hope to see you again soon!");
        } catch(EmptyDescriptionException ed) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");

        } catch(WrongInputException wi) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");

        }
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
            return "|1| " + task;
        } else {
            return "|0| " + task;
        }
    }
}

class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "T" + super.toString();
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
        return "D" + super.toString() + " | " + by;
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
        return "E" + super.toString() + " | " + at;
    }
}

class EmptyDescriptionException extends Exception {

}

class WrongInputException extends Exception {

}