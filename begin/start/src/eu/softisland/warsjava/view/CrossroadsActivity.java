package eu.softisland.warsjava.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.analytics.tracking.android.EasyTracker;
import eu.softisland.warsjava.R;
import eu.softisland.warsjava.view.car.CarActivity;
import eu.softisland.warsjava.view.householder.HouseholderActivity;
import eu.softisland.warsjava.view.map.MapActivity;
import eu.softisland.warsjava.view.webbrowser.WebBrowserActivity;
import eu.softisland.warsjava.view.wifi.WifiActivity;


public class CrossroadsActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crossroads);
    }

    public void openSimpleFragment(View view) {
        Intent intent = new Intent(this, WebBrowserActivity.class);
        startActivity(intent);
    }

    public void openWifiData(View view) {
        Intent intent = new Intent(this, WifiActivity.class);
        startActivity(intent);
    }

    public void openHouseholder(View view) {
        Intent intent = new Intent(this, HouseholderActivity.class);
        startActivity(intent);
    }

    public void openMap(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    public void openCars(View view) {
        Intent intent = new Intent(this, CarActivity.class);
        startActivity(intent);
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