package seedu.address.model.employee;

import seedu.address.logic.commands.AssignTaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import static seedu.address.logic.Messages.MESSAGE_DUPLICATE_TASKID;


/**
 * Represents an Employee's AssignedTasks in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTask(String)}
 */
public class AssignedTasks {

    public static final String MESSAGE_CONSTRAINTS =
            "TASK_IDS should only contain numeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public String tasks;

    /**
     * Constructs a {@code AssignedTasks}.
     *
     * @param tasks A valid name.
     */
    public AssignedTasks(String tasks) {
        requireNonNull(tasks);
        checkArgument(isValidTask(tasks), MESSAGE_CONSTRAINTS);
        this.tasks = tasks;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidTask(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public AssignedTasks updateTask(int taskID) throws CommandException {
        String[] taskArray = tasks.split(" ");

        // Check if taskID matches any of the numbers in tasks
        for (String task : taskArray) {
            if (Integer.parseInt(task) == taskID) {
                throw new CommandException(String.format(MESSAGE_DUPLICATE_TASKID, AssignTaskCommand.MESSAGE_USAGE));            }
        }

        // Add the taskID to tasks
        tasks += " " + taskID;
        return this;
    }
    @Override
    public String toString() {
        return tasks;
    }

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }
}
