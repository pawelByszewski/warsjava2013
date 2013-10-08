package eu.softisland.warsjava.view.map;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import eu.softisland.warsjava.R;
import eu.softisland.warsjava.map.MapAdapter;


public class MapActivity extends FragmentActivity {

    private GoogleMap googleMap;
    private MapAdapter mapAdapter;
    private MapMenuAdapter mapMenuAdapter;
    private LongClickContextMenu longClickContextMenu;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        initMap();
        setLongClickAction();
        mapMenuAdapter = new MapMenuAdapter(mapAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mapMenuAdapter.isMapMenuItem(item)) {
            mapMenuAdapter.handleClick(item);
        }
        return false;
    }

    private void setLongClickAction() {
        longClickContextMenu  = new LongClickContextMenu(this);
        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(final LatLng latLng) {
                longClickContextMenu.setOnClickListener(LongClickContextMenu.MenuItem.PUT_LANDMARK, new LongClickContextMenu.OnClickMenuItem(){

                    @Override
                    public void onClick() {
                        mapAdapter.setLandmarkLocation(latLng);
                    }
                });
                longClickContextMenu.show();
            }
        });
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        this.googleMap = mapFragment.getMap();
        googleMap.setMyLocationEnabled(true);
        mapAdapter = new MapAdapter(googleMap);
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