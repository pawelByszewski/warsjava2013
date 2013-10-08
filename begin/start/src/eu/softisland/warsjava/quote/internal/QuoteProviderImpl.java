package eu.softisland.warsjava.quote.internal;

import eu.softisland.warsjava.quote.QuoteProvider;

public class QuoteProviderImpl extends QuoteProviderBase implements QuoteProvider, QuoteEduroProviderTask.QuoteTaskListener {

    private QuoteEduroProviderTask quoteEduroProviderTask;

    @Override
    public void obtainQuote() {
        super.obtainQuote();
        quoteEduroProviderTask = new QuoteEduroProviderTask(this);
        quoteEduroProviderTask.execute();
    }

    @Override
    public void handleQuoteTaskResult(String quote) {
        this.quoteReceiver.handleQuote(quote);
    }
}
