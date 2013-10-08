package eu.softisland.warsjava.view.webbrowser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


public class WebViewActivity extends FragmentActivity {

    private static final String TAG = WebViewActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebViewFragment webViewFragment = new WebViewFragment();

        Intent i = this.getIntent();
        String link = i.getExtras().getString("link");

        Log.d(TAG, "In separate Activity, open url [" + link + "]");
        webViewFragment.init(link);
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, webViewFragment).commit();
    }

	
}
