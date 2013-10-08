package eu.softisland.warsjava.view.webbrowser;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import eu.softisland.warsjava.R;

public class WebViewFragment extends Fragment {

    private static final String TAG = WebViewFragment.class.getName();

    WebView webView;
    private String currentURL = "http://google.com";

    public WebViewFragment() {
    }

    public void init(String url) {
        currentURL = url;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "Create web view fragment.");
        View view = inflater.inflate(R.layout.web_view, container, false);
        Log.d(TAG, "Load default URL  [" + currentURL + "]");

        webView = (WebView) view.findViewById(R.id.webPage);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new SwAWebClient());
        webView.loadUrl(currentURL);
        return view;
    }

    public void updateUrl(String url) {
        Log.d(TAG, "Update URL ["+url+"] - View ["+getView()+"]");
        currentURL = url;
        webView.loadUrl(url);
    }

    private class SwAWebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

    }



}

