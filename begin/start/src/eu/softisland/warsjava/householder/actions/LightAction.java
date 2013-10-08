package eu.softisland.warsjava.householder.actions;

import android.content.Context;
import eu.softisland.warsjava.householder.ActionType;
import eu.softisland.warsjava.householder.HouseholderAction;
import eu.softisland.warsjava.householder.HouseholderActionListener;
import eu.softisland.warsjava.model.householder.ActionResult;
import eu.softisland.warsjava.utils.CameraAdapter;

public class LightAction implements HouseholderAction, LongTask.ProgressListener {

    private enum Type {ON, OFF};

    private static long[] pattern = { 100, 50, 100, 1500 };
    private HouseholderActionListener householderActionListener;
    private Type type;
    private CameraAdapter cameraAdapter;
    private Context context;
    private ActionType actionType = ActionType.LIGHT;

    public LightAction(Context context) {
        cameraAdapter = CameraAdapter.getInstance();
        this.context = context;
    }

    @Override
    public boolean perform(HouseholderActionListener householderActionListener, boolean checked) {
        this.householderActionListener = householderActionListener;
        if(checked && !cameraAdapter.hasFlash(context)) {
            householderActionListener.handleActionResult(
                    new ActionResult(ActionResult.Status.FAIL, "The light is not present in the device.", actionType));
            return false;
        }
        LongTask longTask = new LongTask(this, pattern);
        longTask.execute();
        type = checked ? Type.ON : Type.OFF;
        return true;
    }

    @Override
    public void onProgressUpdate(Integer progress) {
        if(cameraAdapter.isLightIsTurnOn()) {
            cameraAdapter.turnOfLight();
        } else {
            cameraAdapter.turnOnLight();
        }
        int lightProgress = (int) (((float)progress)  / pattern.length * 100);
        String message = type == Type.ON ? "Turning on the light..." : "Turning off the light...";
        ActionResult actionResult = new ActionResult(lightProgress).setMessage(message);
        householderActionListener.handleActionProgress(actionResult);
    }

    @Override
    public void onTaskComplete(ActionResult.Status status) {
        if(type == Type.OFF) {
            cameraAdapter.turnOfLight();
        } else {
            cameraAdapter.turnOnLight();
        }
        String message = type == Type.OFF ? "The light is turn off." : "The light is turn on.";
        householderActionListener.handleActionResult(
                new ActionResult(ActionResult.Status.OK, message));
    }
}
