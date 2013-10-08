package eu.softisland.warsjava.view.wifi;

import android.app.ListActivity;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.analytics.tracking.android.EasyTracker;
import eu.softisland.warsjava.view.widget.SimpleArrayAdapter;
import eu.softisland.warsjava.wifi.WifiAdapter;

import java.util.ArrayList;
import java.util.List;

public class WifiActivity extends ListActivity implements WifiAdapter.WifiScanResultHandler {

    WifiAdapter wifiAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wifiAdapter = new WifiAdapter(this, this);
        startScan();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Refresh");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        startScan();
        return super.onMenuItemSelected(featureId, item);
    }

    //TODO use WifiAdapter
    protected void onPause() {
        unregisterReceiver(wifiAdapter);
        super.onPause();
    }


    //TODO use WifiAdapter
    protected void onResume() {
        registerReceiver(wifiAdapter, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    @Override
    public void handleScanResult(List<ScanResult> wifiList) {
        setTitle("Scan completed");
        ArrayList list = new ArrayList<String>();
        for(ScanResult wifiData : wifiList) {
            list.add(wifiData.SSID + "    " + wifiData.level );
        }

        final SimpleArrayAdapter adapter = new SimpleArrayAdapter(this, list);
        setListAdapter(adapter);
    }

    private void startScan() {
        setTitle("WIFI scanning...");
        wifiAdapter.startScan();
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
}
