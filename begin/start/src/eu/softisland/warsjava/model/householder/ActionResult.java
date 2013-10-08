package eu.softisland.warsjava.model.householder;

import eu.softisland.warsjava.householder.ActionType;

public class ActionResult {
    public enum Status {OK, FAIL}

    private Status status;
    private String message;
    private int progress;
    private ActionType actionType;

    public ActionResult(Status status, String message) {
        this.status = status;
        this.message = message;
        this.progress = 100;
    }

    public ActionResult(Status status, String message, ActionType actionType) {
        this.status = status;
        this.message = message;
        this.progress = 100;
        this.actionType = actionType;
    }

    public ActionResult(Status status) {
        this.status = status;
    }

    public ActionResult(int progress) {
        this.progress = progress;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ActionResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public int getProgress() {
        return progress;
    }

    public ActionType getActionType() {
        return actionType;
    }
}
