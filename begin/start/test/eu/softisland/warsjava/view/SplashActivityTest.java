package eu.softisland.warsjava.view;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import eu.softisland.warsjava.R;
import eu.softisland.warsjava.quote.QuoteProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;

@RunWith(RobolectricTestRunner.class)
public class SplashActivityTest {

    @Mock
    private QuoteProvider quoteProvider;
    private ActivityController<SplashActivity> activityController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        activityController = Robolectric.buildActivity(SplashActivity.class);
    }

    @Test
    public void checkTextOfQuote() throws Exception {
        //given
        final String quote = "Mock Quote";
        final SplashActivity activity = activityController.get();
        activity.setQuoteProvider(quoteProvider);
           doAnswer(new Answer() {
               @Override
               public Object answer(InvocationOnMock invocation) throws Throwable {
                   activity.handleQuote(quote);
                   return null;
               }
           }).when(quoteProvider).obtainQuote();

        //when
        activityController.create();
        TextView quotationTextView = (TextView) activity.findViewById(R.id.splash_quotation);
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.splash_progressBar);

        //then
        assertEquals(quotationTextView.getVisibility(), View.VISIBLE);
        assertEquals(progressBar.getVisibility(), View.GONE);
        assertEquals(quote, quotationTextView.getText());
    }

}
