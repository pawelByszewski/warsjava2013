package eu.softisland.warsjava.quote;

public interface QuoteProvider {

    void setQuatationReceiver(QuoteReceiver quoteReceiver);

    void obtainQuote();
}
