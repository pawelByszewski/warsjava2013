package eu.softisland.warsjava.quote;

import com.google.inject.ImplementedBy;
import eu.softisland.warsjava.quote.internal.QuoteProviderMock;

@ImplementedBy(QuoteProviderMock.class)
public interface QuoteProvider {

    void setQuatationReceiver(QuoteReceiver quoteReceiver);

    void obtainQuote();
}
