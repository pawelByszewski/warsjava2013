package eu.softisland.warsjava.quote.internal;

import android.os.Handler;
import eu.softisland.warsjava.quote.QuoteProvider;

public class QuoteProviderMock extends QuoteProviderBase implements QuoteProvider {

    private static int FAKE_DELAY = 5 * 1000;
    private static String FAKE_QUOTE = "... never give in except to convictions of honour and good sense.";

    @Override
    public void obtainQuote() {
        super.obtainQuote();
        if(quoteReceiver == null) {
            throw new IllegalStateException("U have to set receiver first.");
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                quoteReceiver.handleQuote(FAKE_QUOTE);
            }

        }, FAKE_DELAY);
    }
}
