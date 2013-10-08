package eu.softisland.warsjava.view.map;

import android.view.MenuItem;
import eu.softisland.warsjava.map.MapAdapter;
import eu.softisland.warsjava.model.map.MapType;

public class MapMenuAdapter {

    private MapAdapter mapAdapter;

    public MapMenuAdapter(MapAdapter mapAdapter) {
        this.mapAdapter = mapAdapter;
    }

    public boolean isMapMenuItem(MenuItem item) {
        return MapType.contains(item);
    }

    public void handleClick(MenuItem item) {
        mapAdapter.setMapType(MapType.extractAndroidMapType(item));
    }
}
