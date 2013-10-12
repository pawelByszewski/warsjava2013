package eu.softisland.warsjava.quote.internal;

import com.google.inject.Inject;
import eu.softisland.warsjava.quote.QuoteProvider;

public class QuoteProviderImpl extends QuoteProviderBase implements QuoteProvider, QuoteEduroProviderTask.QuoteTaskListener {


    @Inject
    private QuoteEduroProviderTask quoteEduroProviderTask;

    @Override
    public void obtainQuote() {
        super.obtainQuote();
        quoteEduroProviderTask.setQuoteTaskListener(this);
        quoteEduroProviderTask.execute();
    }

    @Override
    public void handleQuoteTaskResult(String quote) {
        this.quoteReceiver.handleQuote(quote);
    }
}
