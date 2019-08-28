import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

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

    private static Calendar inputConvertToCalendar(String input) {
        String findDate[] = input.split("/");
        int day = Integer.parseInt(findDate[0]);
        int month = Integer.parseInt(findDate[1]) - 1;
        String findTime[] = findDate[2].split(" ");
        int year = Integer.parseInt(findTime[0]);
        int hour = Integer.parseInt(findTime[1].substring(0, 2));
        int minute = Integer.parseInt(findTime[1].substring(2));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute);
        return calendar;
    }

    private static int convertToInt(String month) {
        if (month.equals("January")) {
            return 0;
        }
        else if (month.equals("February")) {
            return 1;
        }
        else if (month.equals("March")) {
            return 2;
        }
        else if (month.equals("April")) {
            return 3;
        }
        else if (month.equals("May")) {
            return 4;
        }
        else if (month.equals("June")) {
            return 5;
        }
        else if (month.equals("July")) {
            return 6;
        }
        else if (month.equals("August")) {
            return 7;
        }
        else if (month.equals("September")) {
            return 8;
        }
        else if (month.equals("October")) {
            return 9;
        }
        else if (month.equals("November")) {
            return 10;
        } else {
            return 11;
        }
    }

    private static Calendar fileConvertToCalendar(String input) {
        String findDate[] = input.split(" ");
        int day = Integer.parseInt(findDate[0]);
        int month = convertToInt(findDate[1]);
        int year = Integer.parseInt(findDate[2]);
        int hour = Integer.parseInt(findDate[3].substring(0, 2));
        int min = Integer.parseInt(findDate[3].substring(2));
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, min);
        return calendar;
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
                String line = fileScanner.nextLine();
                String[] words = line.split("\\|");
                if (words[0].equals("T")) {
                    Todo task = new Todo(words[2]);
                    inputs.add(task);
                }
                else if (words[0].equals("D")) {
                    Deadline task = new Deadline(words[2], fileConvertToCalendar(words[3].substring(1)));
                    inputs.add(task);
                }
                else if (words[0].equals("E")) {
                    Event task = new Event(words[2], fileConvertToCalendar(words[3].substring(1)));
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
                    String findTask[] = findDeadline[0].split(" ");
                    String findDate[] = input.split("by ");
                    Calendar calendar = inputConvertToCalendar(findDate[1]);
                    String deadlineTask = findTask[1];
                    for (int i = 2; i < findTask.length; i++) {
                        deadlineTask += " " + findTask[i];
                    }
                    Deadline deadline = new Deadline(deadlineTask, calendar);
                    //System.out.println(calendar);
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
                    String findDate[] = input.split("at ");
                    Calendar calendar = inputConvertToCalendar(findDate[1]);
                    String findTask[] = findTime[0].split(" ");
                    String eventTask = findTask[1];
                    for (int i = 2; i < findTask.length; i++) {
                        eventTask += " " + findTask[i];
                    }
                    Event event = new Event(eventTask, calendar);
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

    public String getMonth(Calendar c) {
        if (c.get(Calendar.MONTH) == 0) {
            return "January";
        }
        else if (c.get(Calendar.MONTH) == 1) {
            return "February";
        }
        else if (c.get(Calendar.MONTH) == 2) {
            return "March";
        }
        else if (c.get(Calendar.MONTH) == 3) {
            return "April";
        }
        else if (c.get(Calendar.MONTH) == 4) {
            return "May";
        }
        else if (c.get(Calendar.MONTH) == 5) {
            return "June";
        }
        else if (c.get(Calendar.MONTH) == 6) {
            return "July";
        }
        else if (c.get(Calendar.MONTH) == 7) {
            return "August";
        }
        else if (c.get(Calendar.MONTH) == 8) {
            return "September";
        }
        else if (c.get(Calendar.MONTH) == 9) {
            return "October";
        }
        else if (c.get(Calendar.MONTH) == 10) {
            return "November";
        }
        else {
            return "December";
        }
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
    Calendar by;

    public Deadline(String task, Calendar by) {
        super(task);
        this.by = by;
    }

    @Override
    public String toString() {
        return "D" + super.toString() + " | " + by.get(Calendar.DAY_OF_MONTH) + " "
                + getMonth(by) + " " + by.get(Calendar.YEAR) + " "
                    + by.get(Calendar.HOUR_OF_DAY)
                        + String.format("%02d", by.get(Calendar.MINUTE));
    }
}

class Event extends Task {
    Calendar at;

    public Event(String task, Calendar at) {
        super(task);
        this.at = at;
    }

    @Override
    public String toString() {
        return "E" + super.toString() + " | " + at.get(Calendar.DAY_OF_MONTH) + " "
                + getMonth(at) + " " + at.get(Calendar.YEAR) + " "
                + at.get(Calendar.HOUR_OF_DAY)
                + String.format("%02d", at.get(Calendar.MINUTE));
    }
}

class EmptyDescriptionException extends Exception {

}

class WrongInputException extends Exception {

}