package eu.softisland.warsjava.view.householder;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.*;
import com.google.analytics.tracking.android.EasyTracker;
import eu.softisland.warsjava.R;
import eu.softisland.warsjava.householder.ActionFactory;
import eu.softisland.warsjava.householder.ActionType;
import eu.softisland.warsjava.householder.HouseholderAction;
import eu.softisland.warsjava.householder.HouseholderActionListener;
import eu.softisland.warsjava.model.householder.ActionResult;

public class HouseholderActivity extends Activity implements HouseholderActionListener {

    private LinearLayout progressBox;
    private ProgressBar progressBar;
    private TextView progressInfo;
    private ActionFactory actionFactory = new ActionFactory();
    private int RESULT_DURATION = 3 * 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.householder);

        progressBox = (LinearLayout) findViewById(R.id.progressBox);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressInfo = (TextView) findViewById(R.id.progressInfo);

    }

    public void onSwitchClick(View view) {
        HouseholderAction action = actionFactory.createAction(view, this);
        showProgress();
        action.perform(this, ((Switch)view).isChecked());
        enableActions(false);
    }

    public void onButtonClick(View view) {
        HouseholderAction action = actionFactory.createAction(view, this);
        showProgress();
        action.perform(this, true);
        enableActions(false);
    }

    @Override
    public void handleActionProgress(ActionResult actionResult) {
        progressBar.setProgress(actionResult.getProgress());
        progressInfo.setText(actionResult.getMessage());
    }


    @Override
    public void handleActionResult(ActionResult actionResult) {
        if(actionResult.getStatus() == ActionResult.Status.FAIL) {
            restorePreviousStateForSwitchItem(actionResult);
        }

        if(actionResult.getStatus() == ActionResult.Status.OK) {
            progressBar.setProgress(100);
        }

        progressInfo.setText(actionResult.getMessage());
        hideProgressIn(RESULT_DURATION);
    }

    private void hideProgressIn(int hideProgressDelay) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideProgress();
                enableActions(true);
            }
        }, hideProgressDelay);
    }

    private void restorePreviousStateForSwitchItem(ActionResult actionResult) {
        if(actionResult.getActionType().isSwitch()) {
            Switch item = (Switch) findViewById(actionResult.getActionType().getId());
            item.setChecked(!item.isChecked());
        }
    }

    private void showProgress() {
        progressBox.setVisibility(View.VISIBLE);
        progressBar.setProgress(0);
        progressInfo.setText("In progress...");
    }

    private void hideProgress() {
        progressBar.setProgress(100);
        progressBox.setVisibility(View.INVISIBLE);
    }

    private void enableActions(boolean enable) {
        for(ActionType actionType : ActionType.values()) {
            View view = findViewById(actionType.getId());
            view.setEnabled(enable);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);
    }
}
