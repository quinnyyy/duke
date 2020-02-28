package duke.commands;

import duke.Main;
import duke.exceptions.BadLineFormatException;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.time.LocalDateTime;

/**
 * Class to represent by command
 */
public class ByCommand extends Command {
    public static final String KEYWORD = "by";
    public static final String EXAMPLE_USAGE = "by 24/6/2022 16:30";
    private String message;
    private LocalDateTime dateTime;

    public ByCommand(String keyword, String[] tokens, TaskList taskList) throws BadLineFormatException {
        super(keyword, tokens, taskList);
        if (tokens.length != 3) {
            throw new BadLineFormatException("Command needs to be in form: by d/M/yyyy H:mm");
        }
        this.dateTime = LocalDateTime.parse(tokens[1] + " " + tokens[2], Main.DTF);
        this.message = "These are your tasks by " + this.dateTime + ":";
        this.isPersistentCommand = false;
    }

    /**
     * List all tasks that occur before target dateTime
     */
    public void execute() {
        String tasks = taskList.getTasksByFilter((Task t) -> t.getIsBy(dateTime));
        Ui.printPretty(message + tasks);
    }
}