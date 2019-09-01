import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                parser = new Parser(fullCommand);
                Command c = parser.parse();
                c.execute(tasks, fullCommand, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.showError(e.getMessage());
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("/Users/sheryl/CS2103/duke/src/main/java/Duke.txt").run();
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
    Calendar from;
    Calendar to;


    public Event(String task, Calendar from, Calendar to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "E" + super.toString() + " | " + from.get(Calendar.DAY_OF_MONTH) + " "
                + getMonth(from) + " " + from.get(Calendar.YEAR) + " "
                + from.get(Calendar.HOUR_OF_DAY)
                + String.format("%02d", from.get(Calendar.MINUTE)) + "-"
                + to.get(Calendar.HOUR_OF_DAY)
                + String.format("%02d", to.get(Calendar.MINUTE));
    }
}
