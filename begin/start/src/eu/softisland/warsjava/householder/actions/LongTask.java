package eu.softisland.warsjava.householder.actions;

import android.os.AsyncTask;
import eu.softisland.warsjava.model.householder.ActionResult;

class LongTask extends AsyncTask<Integer, Integer, ActionResult.Status> {

    private ProgressListener progressListener;
    private long[] stepsPattern;

    LongTask(ProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    public LongTask(ProgressListener progressListener, long[] pattern) {
        this.progressListener = progressListener;
        this.stepsPattern = pattern;
    }

    @Override
    protected ActionResult.Status doInBackground(Integer... params) {
        if(progressListener == null) {
            throw new RuntimeException("ProgressListener must be preset.");
        }
        if(!paramsOrPatternExists(params)) {
            throw new RuntimeException("You have to provide at least one parameter - task duration.");
        }
        if(stepsPattern != null) {
            performStepsWithPatter();
        } else {
            performStepsWithParams(params);
        }
        return ActionResult.Status.OK;
    }

    @Override
    protected void onPostExecute(ActionResult.Status status) {
        super.onPostExecute(status);
        progressListener.onTaskComplete(status);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressListener.onProgressUpdate(values[0]);
    }

    private void performStepsWithPatter() {
        for(int i=0; i< stepsPattern.length; i++) {
            publishProgress(i);
            try {
                Thread.sleep(stepsPattern[i]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void performStepsWithParams(Integer... params) {
        int duration = params[0];
        int stepsCount = params.length == 2 ? params[1] : 100;
        for(int i=0; i< stepsCount; i++) {
            publishProgress(i);
            try {
                Thread.sleep(duration/stepsCount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean paramsOrPatternExists(Integer[] params) {
        return this.stepsPattern != null || (params.length > 0 && params[0] instanceof Integer);
    }

    static interface ProgressListener {
        void onProgressUpdate(Integer progress);
        void onTaskComplete(ActionResult.Status status);

    }
}
