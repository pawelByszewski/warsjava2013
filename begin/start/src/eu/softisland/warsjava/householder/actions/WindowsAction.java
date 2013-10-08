package eu.softisland.warsjava.householder.actions;

import android.content.Context;
import android.os.Vibrator;
import eu.softisland.warsjava.householder.HouseholderAction;
import eu.softisland.warsjava.householder.HouseholderActionListener;
import eu.softisland.warsjava.model.householder.ActionResult;

public class WindowsAction implements HouseholderAction, LongTask.ProgressListener {

    private enum Type {OPEN, CLOSE};

    private static long[] pattern = { 0, 100, 50, 100 };
    private Vibrator vibrator;
    private HouseholderActionListener householderActionListener;
    private int windowsProccesedCount = 0;
    private int windowsCount = 5;
    private Type type;

    public WindowsAction(Context context) {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public boolean perform(HouseholderActionListener householderActionListener, boolean checked) {
        this.householderActionListener = householderActionListener;
        LongTask longTask = new LongTask(this);
        longTask.execute(8000, windowsCount);
        type = checked ? Type.OPEN : Type.CLOSE;
        return true;
    }

    @Override
    public void onProgressUpdate(Integer progress) {
        vibrator.vibrate(100);
        int closingProgress = (int) (((float)progress)  / windowsCount * 100);
        String messagePrefix = type == Type.OPEN ? "Opening windows " : "Closing windows ";
        ActionResult actionResult = new ActionResult(closingProgress).setMessage(messagePrefix + ++windowsProccesedCount + "/" + windowsCount);
        householderActionListener.handleActionProgress(actionResult);
    }

    @Override
    public void onTaskComplete(ActionResult.Status status) {
        vibrator.vibrate(pattern, -1);
        String message = type == Type.OPEN ? "All windows opened." : "All windows closed.";
        householderActionListener.handleActionResult(
                new ActionResult(ActionResult.Status.OK, message));
    }
}
