import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            parser = new Parser(input);
            Command c = parser.parse();
            return c.execute(tasks, input, storage, ui);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "Guess there's something wrong with your file so u can't do anything today";
        }
    }

    /**
     * Constructor for Duke which creates new file.
     */
    public Duke() {
        ui = new Ui();
        try {
            String newDirectoryPath = System.getProperty("user.dir") + File.separator + "Lib";
            File newDirectory = new File(newDirectoryPath);
            String newFilePath = newDirectoryPath + File.separator + "Hermione.txt";
            File newFile = new File(newFilePath);
            boolean canCreateDirectory = newDirectory.mkdir();
            if (canCreateDirectory) {
                newFile.createNewFile();
                System.out.println("New file and directory created.");
            } else {
                System.out.println("Directory already exists");
            }
            storage = new Storage(newFilePath);
            tasks = storage.load();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Constructor for Duke with file path as parameter.
     * @param filePath String representing file path.
     */
    public Duke(String filePath) {
        assert filePath != null : "file path cannot be null";
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
                c.execute(tasks, fullCommand, storage, ui);
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

