import java.util.Calendar;

public class Parser {
    private String input;

    public Parser(String input) {
        this.input = input;
    }

    /**
     * returns a Command which handles user input.
     * @return a Command.
     * @throws DukeException if user input is incorrect.
     */
    public Command parse() throws DukeException {
        String[] line = input.split(" ");
        if (line[0].equals("todo") || line[0].equals("deadline")
            || line[0].equals("event")) {
            return new AddCommand();
        } else if (line[0].equals("done")) {
            return new DoneCommand();
        } else if (line[0].equals("delete")) {
            return new DeleteCommand();
        } else if (line[0].equals("list")) {
            return new ListCommand();
        } else if (line[0].equals("bye")) {
            return new ExitCommand();
        } else if (line[0].equals("find")) {
            return new FindCommand();
        } else if (line[0].equals("update")) {
            return new UpdateCommand();
        } else {
            throw new DukeException();
        }
    }

}
