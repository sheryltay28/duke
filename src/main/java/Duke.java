import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;

public class
Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        // more code to be added here later
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    public Duke() {

    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * calls different classes to process user input.
     */
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

    /**
     * returns a string which represents the task.
     * @return a String.
     */
    public String getTask() {
        return task;
    }

    /**
     * to set task as done.
     */
    public void doTask() {
        this.done = true;
    }

    /**
     * returns a string which is the month corresponding to int of month in calendar.
     * @param c to get integer representing month.
     * @return the string which represents month.
     */
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

    /**
     * returns string representation of task object.
     * @return string.
     */
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

    /**
     * returns string representation of task object.
     * @return string.
     */
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

    /**
     * returns string representation of task object.
     * @return string.
     */
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

    /**
     * returns string representation of task object.
     * @return string.
     */
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
