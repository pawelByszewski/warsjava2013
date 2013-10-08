package eu.softisland.warsjava.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.List;

public class WifiAdapter extends BroadcastReceiver {

    private Context context;
    private WifiScanResultHandler wifiScanResultHandler;
    WifiManager mainWifi;
    List<ScanResult> wifiList;

    public WifiAdapter(Context context, WifiScanResultHandler wifiScanResultHandler) {
        this.context = context;
        this.wifiScanResultHandler = wifiScanResultHandler;
        mainWifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }

    public void startScan() {
        context.registerReceiver(this, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        mainWifi.startScan();
    }

    public void onReceive(Context c, Intent intent) {
        wifiList = mainWifi.getScanResults();
        wifiScanResultHandler.handleScanResult(wifiList);
    }

    public interface WifiScanResultHandler {
        void handleScanResult(List<ScanResult> wifiList);
    }

}
