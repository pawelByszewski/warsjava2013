package eu.softisland.warsjava.quote.internal;

import eu.softisland.warsjava.quote.QuoteProvider;
import eu.softisland.warsjava.quote.QuoteReceiver;

public abstract class QuoteProviderBase implements QuoteProvider {

    protected QuoteReceiver quoteReceiver;

    @Override
    public void setQuatationReceiver(QuoteReceiver quoteReceiver) {
        this.quoteReceiver = quoteReceiver;
    }

    @Override
    public void obtainQuote() {
        if(quoteReceiver == null) {
            throw new RuntimeException("QuoteReceiver must be set, before 'obtainQuote' is called.");
        }
    }
}
