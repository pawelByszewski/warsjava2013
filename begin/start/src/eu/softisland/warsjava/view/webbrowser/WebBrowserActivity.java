package eu.softisland.warsjava.view.webbrowser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import eu.softisland.warsjava.R;


public class WebBrowserActivity extends FragmentActivity implements BookmarksFragment.ChangeLinkListener {

    private static final String TAG = WebBrowserActivity.class.getName();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webbrowser);
    }

    @Override
    public void onLinkChange(String link) {

        if (containsWebViewFragment()) {
            WebViewFragment webViewFragment = (WebViewFragment) getSupportFragmentManager().findFragmentById(R.id.fragPage);
            Log.d(TAG, "Open link on the same screen");
            webViewFragment.updateUrl(link);
        } else {
            Log.d(TAG, "Open new activity for web");
            Intent i = new Intent(this, WebViewActivity.class);
            i.putExtra("link", link);
            startActivity(i);
        }
    }

    private boolean containsWebViewFragment() {
        return findViewById(R.id.fragPage) != null;
    }
}