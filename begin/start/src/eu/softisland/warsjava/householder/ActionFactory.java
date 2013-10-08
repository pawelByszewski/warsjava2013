package eu.softisland.warsjava.householder;

import android.content.Context;
import android.view.View;
import eu.softisland.warsjava.householder.actions.*;

public class ActionFactory {
    public HouseholderAction createAction(View view, Context context) {
        ActionType actionType = ActionType.extractAction(view.getId());
        switch (actionType) {
            case COFFEE:
                return new CoffeeAction(context);
            case FEEDING:
                return new FeedingAction(context);
            case WINDOWS:
                return new WindowsAction(context);
            case LIGHT:
                return new LightAction(context);
            case SHADES:
                return new ShadesAction(context);
            case CLEANER:
                return new CleanerAction(context);
            case HEATING:
                return new HeatingAction();
        }

        throw new RuntimeException("The is not any implementation for action ");
    }
}
