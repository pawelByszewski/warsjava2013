package eu.softisland.warsjava.householder;

import eu.softisland.warsjava.model.householder.ActionResult;

public interface HouseholderActionListener {

    void handleActionResult(ActionResult actionResult);

    void handleActionProgress(ActionResult actionResult);
}
