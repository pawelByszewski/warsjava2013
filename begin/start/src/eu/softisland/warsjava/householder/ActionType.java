package eu.softisland.warsjava.householder;

import eu.softisland.warsjava.R;

public enum ActionType {

    COFFEE(R.id.coffeeButton),
    FEEDING(R.id.feedingButton),
    HEATING(R.id.heatingSwitch, true),
    WINDOWS(R.id.windowsSwitch, true),
    SHADES(R.id.shadesSwitch, true),
    CLEANER(R.id.CleanerSwitch, true),
    LIGHT(R.id.lightSwitch, true);

    int typeViewId;
    private boolean isSwitch;

    private ActionType(int typeViewId) {
        this.typeViewId = typeViewId;
        this.isSwitch = false;
    }

    private ActionType(int typeViewId, boolean isSwitch) {
        this.typeViewId = typeViewId;
        this.isSwitch = isSwitch;
    }

    public int getId() {
        return typeViewId;
    }

    public static ActionType extractAction(int viewId) {
        for(ActionType actionType : ActionType.values()) {
            if(actionType.getId() == viewId) {
                return actionType;
            }
        }
        throw new RuntimeException("No action type for view id: " + viewId);
    }

    public boolean isSwitch() {
        return isSwitch;
    }
}
