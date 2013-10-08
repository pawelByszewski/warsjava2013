package eu.softisland.warsjava.householder.actions;

import android.content.Context;
import android.os.Vibrator;
import eu.softisland.warsjava.householder.HouseholderAction;
import eu.softisland.warsjava.householder.HouseholderActionListener;
import eu.softisland.warsjava.model.householder.ActionResult;

public class FeedingAction implements HouseholderAction, LongTask.ProgressListener {

    private static long[] pattern = { 0, 100, 50, 100 };
    private Vibrator vibrator;
    private HouseholderActionListener householderActionListener;

    public FeedingAction(Context context) {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public boolean perform(HouseholderActionListener householderActionListener, boolean checked) {
        this.householderActionListener = householderActionListener;
        vibrator.vibrate(100);
        LongTask longTask = new LongTask(this);
        longTask.execute(4000);
        return true;
    }

    @Override
    public void onProgressUpdate(Integer progress) {
        ActionResult actionResult = new ActionResult(progress).setMessage("Preparing portion...");
        householderActionListener.handleActionProgress(actionResult);
    }

    @Override
    public void onTaskComplete(ActionResult.Status status) {
        vibrator.vibrate(pattern, -1);
        householderActionListener.handleActionResult(
                new ActionResult(ActionResult.Status.OK, "New portion of foodstuff released."));
    }
}
