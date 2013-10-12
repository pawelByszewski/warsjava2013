package eu.softisland.warsjava.quote.internal;

import android.content.Context;
import android.util.Log;
import com.google.inject.Inject;
import eu.softisland.warsjava.utils.HttpUtils;
import roboguice.util.RoboAsyncTask;

public class QuoteEduroProviderTask extends RoboAsyncTask<String> {

    private static final String TAG = QuoteEduroProviderTask.class.getName();

    private static String SERVER_CITE_URL = "http://www.eduro.com/";

    private QuoteTaskListener quoteTaskListener;

    @Inject
    private QuoteEduroProviderTask(Context context) {
        super(context);
    }


    public void setQuoteTaskListener(QuoteTaskListener quoteTaskListener) {
        this.quoteTaskListener = quoteTaskListener;
    }

    @Override
    public String call() throws Exception {
        String html = HttpUtils.getContent(SERVER_CITE_URL);
        return extractQuote(html);
    }

    @Override
    protected void onException(Exception e) throws RuntimeException {
        super.onException(e);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void onThrowable(Throwable t) throws RuntimeException {
        super.onThrowable(t);    //To change body of overridden methods use File | Settings | File Templates.
    }

    private String extractQuote(String html) {
        int quoteDivStart = html.indexOf("<dailyquote>");
        int quoteDivEnd = html.indexOf("</dailyquote>");
        String quoteDiv = html.substring(quoteDivStart, quoteDivEnd);
        Log.d(TAG, "The whole quote div: " + quoteDiv);
        int quoteStart = quoteDiv.indexOf("<p>");
        int quoteEnd = quoteDiv.indexOf("</p>");
        String quote = quoteDiv.substring(quoteStart + "<p>".length(),quoteEnd);
        Log.d(TAG, "The extracted quote: " + quote);
        return quote;
    }


    @Override
    protected void onSuccess(String s) throws Exception {
        quoteTaskListener.handleQuoteTaskResult(s);
    }

    public interface QuoteTaskListener {
        public void handleQuoteTaskResult(String quote);
    }

}
