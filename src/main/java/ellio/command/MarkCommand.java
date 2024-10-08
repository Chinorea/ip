package ellio.command;

import ellio.BotText;
import ellio.EllioExceptions;
import ellio.storage.Storage;
import ellio.task.TaskList;
import ellio.task.Todo;
import ellio.ui.Ui;

import static ellio.Ellio.storage;

public class MarkCommand extends Command {

    public MarkCommand(String inputCommand) {
        super(inputCommand);
    }


    /**
     * Extract the task from the list and marks it as completed
     * Updates both the list and the save File
     * @param tasks Input by user
     * @param ui Ui interface reference
     * @param storage Reference for Storage functions
     */
    public void execute(TaskList tasks, Ui ui, Storage storage)throws EllioExceptions{
        int index = Integer.parseInt(tasks.getTaskIndex(inputCommand));
        if(index > tasks.getNumberTask()){
            throw new EllioExceptions.OutOfIndexException(tasks.getNumberTask());
        }
        tasks.getTask(index-1).markTaskAsDone();
        storage.updateSavedTaskFile();
        ui.showMarkMessage(tasks.getTask(index-1));
    }
}
