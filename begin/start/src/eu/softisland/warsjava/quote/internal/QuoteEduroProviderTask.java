package eu.softisland.warsjava.quote.internal;

import android.os.AsyncTask;
import android.util.Log;
import eu.softisland.warsjava.utils.HttpUtils;

import java.io.IOException;

public class QuoteEduroProviderTask extends AsyncTask<Void, Integer, String> {

    private static final String TAG = QuoteEduroProviderTask.class.getName();

    private static String SERVER_CITE_URL = "http://www.eduro.com/";

    private QuoteTaskListener quoteTaskListener;

    public QuoteEduroProviderTask(QuoteTaskListener quoteTaskListener) {
        this.quoteTaskListener = quoteTaskListener;
    }

    @Override
    protected String doInBackground(Void... params) {
        String html = "";
        try {
            html = HttpUtils.getContent(SERVER_CITE_URL);
            return extractQuote(html);
        } catch (IOException e) {
            //TODO check what heppen if exception is thrown and not catch
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
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
    protected void onPostExecute(String quote) {
        quoteTaskListener.handleQuoteTaskResult(quote);
    }

    public interface QuoteTaskListener {
        public void handleQuoteTaskResult(String quote);
    }

}
