package eu.softisland.warsjava.householder.actions;

import eu.softisland.warsjava.householder.ActionType;
import eu.softisland.warsjava.householder.HouseholderAction;
import eu.softisland.warsjava.householder.HouseholderActionListener;
import eu.softisland.warsjava.model.householder.ActionResult;

public class HeatingAction implements HouseholderAction, LongTask.ProgressListener {

    private enum Type {OPEN, CLOSE};

    private HouseholderActionListener householderActionListener;
    private ActionType actionType = ActionType.HEATING;

    @Override
    public boolean perform(HouseholderActionListener householderActionListener, boolean checked) {
        this.householderActionListener = householderActionListener;
        LongTask longTask = new LongTask(this);
        longTask.execute(4000);
        return true;
    }

    @Override
    public void onProgressUpdate(Integer progress) {
        ActionResult actionResult = new ActionResult(progress).setMessage("Try to turn off heating ... ");
        householderActionListener.handleActionProgress(actionResult);
    }

    @Override
    public void onTaskComplete(ActionResult.Status status) {
        String message = "Dude, it is too cold outside. It would be madness to turn off heating.";
        householderActionListener.handleActionResult(
                new ActionResult(ActionResult.Status.FAIL, message, actionType));
    }
}
