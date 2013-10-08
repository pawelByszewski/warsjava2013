package eu.softisland.warsjava.view;

import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import eu.softisland.warsjava.R;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class SplashActivityTest {

    @Test
    public void clickingButton_shouldChangeResultsViewText() throws Exception {
        Activity activity = Robolectric.buildActivity(SplashActivity.class).create().get();

        TextView quotationTextView = (TextView) activity.findViewById(R.id.splash_quotation);
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.splash_progressBar);

//        pressMeButton.performClick();
//        String resultsText = results.tText().toString();
        assertEquals(quotationTextView.getVisibility(), View.VISIBLE);
        assertEquals(progressBar.getVisibility(), View.GONE);
        assertEquals("... never give in except to convictions of honour and good sense.", quotationTextView.getText());
    }

}
