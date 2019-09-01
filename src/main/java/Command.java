import java.io.IOException;

abstract class Command {
    abstract void execute(TaskList tasks, String input, Storage storage) throws
            DukeException, IOException;
    abstract boolean isExit();
}
