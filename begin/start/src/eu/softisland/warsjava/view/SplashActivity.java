package eu.softisland.warsjava.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import eu.softisland.warsjava.R;
import eu.softisland.warsjava.quote.QuoteProvider;
import eu.softisland.warsjava.quote.QuoteReceiver;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.splash)
public class SplashActivity extends RoboActivity implements QuoteReceiver {

    @Inject @Named("qwe")
    private int SPLASH_SCREEN_DURATION;

    @InjectView(R.id.splash_quotation)
    private TextView quotationTextView;
    private ProgressBar progressBar;

    @Inject
    private QuoteProvider quoteProvider;
//    private QuoteProvider quoteProvider = new QuoteProviderImpl();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
