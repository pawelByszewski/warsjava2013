package eu.softisland.warsjava.householder.actions;

import android.content.Context;
import android.os.Vibrator;
import eu.softisland.warsjava.householder.HouseholderAction;
import eu.softisland.warsjava.householder.HouseholderActionListener;
import eu.softisland.warsjava.model.householder.ActionResult;

public class CoffeeAction implements HouseholderAction {

    private static long[] pattern = { 0, 100, 50, 100, 100, 200 };
    private Vibrator vibrator;

    public CoffeeAction(Context context) {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public boolean perform(HouseholderActionListener householderActionListener, boolean checked) {
        vibrator.vibrate(pattern, -1);
        householderActionListener.handleActionResult(
                new ActionResult(ActionResult.Status.OK, "Coffee will be ready in 30 second."));
        return true;
    }
}
