package eu.softisland.warsjava.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.analytics.tracking.android.EasyTracker;
import eu.softisland.warsjava.R;
import eu.softisland.warsjava.quote.QuoteProvider;
import eu.softisland.warsjava.quote.QuoteReceiver;
import eu.softisland.warsjava.quote.internal.QuoteProviderMock;

public class SplashActivity extends Activity implements QuoteReceiver {

    private static int SPLASH_SCREEN_DURATION = 1 * 1000;

    private TextView quotationTextView;
    private ProgressBar progressBar;

    private QuoteProvider quoteProvider = new QuoteProviderMock();
//    private QuoteProvider quoteProvider = new QuoteProviderImpl();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        quotationTextView = (TextView) findViewById(R.id.splash_quotation);
        progressBar = (ProgressBar) findViewById(R.id.splash_progressBar);

        quoteProvider.setQuatationReceiver(this);
        quoteProvider.obtainQuote();
    }

    @Override
    public void handleQuote(String quote) {
        quotationTextView.setText(quote);
        quotationTextView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        startApplication();
    }

    private void startApplication() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                SplashActivity.this.finish();
                Intent intent = new Intent(SplashActivity.this, CrossroadsActivity.class);
                SplashActivity.this.startActivity(intent);

            }

        }, SPLASH_SCREEN_DURATION);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStop(this);
    }

    protected void setQuoteProvider(QuoteProvider quoteProvider) {
        this.quoteProvider = quoteProvider;
    }
}
