package eu.softisland.warsjava.householder.actions;

import android.content.Context;
import android.os.Vibrator;
import eu.softisland.warsjava.householder.ActionType;
import eu.softisland.warsjava.householder.HouseholderAction;
import eu.softisland.warsjava.householder.HouseholderActionListener;
import eu.softisland.warsjava.model.householder.ActionResult;

public class CleanerAction implements HouseholderAction, LongTask.ProgressListener {

    private enum Type {OPEN, CLOSE};

    private static long[] pattern = { 0, 100, 50, 100, 50, 100, 50, 100 };
    private Vibrator vibrator;
    private HouseholderActionListener householderActionListener;
    private ActionType actionType = ActionType.CLEANER;

    public CleanerAction(Context context) {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public boolean perform(HouseholderActionListener householderActionListener, boolean checked) {
        this.householderActionListener = householderActionListener;
        LongTask longTask = new LongTask(this);
        longTask.execute(4000);
        vibrator.vibrate(50);
        return true;
    }

    @Override
    public void onProgressUpdate(Integer progress) {
        ActionResult actionResult = new ActionResult(progress).setMessage("Try to start vacum cleaner ... ");
        householderActionListener.handleActionProgress(actionResult);
    }

    @Override
    public void onTaskComplete(ActionResult.Status status) {
        vibrator.vibrate(pattern, -1);
        String message = "Vacum cleaner does not respond.";
        householderActionListener.handleActionResult(
                new ActionResult(ActionResult.Status.FAIL, message, actionType));
    }
}
