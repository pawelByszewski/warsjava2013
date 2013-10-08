package eu.softisland.warsjava.model.map;

import android.util.Log;
import android.view.MenuItem;
import com.google.android.gms.maps.GoogleMap;
import eu.softisland.warsjava.R;

public enum MapType {
    SIMPLE(GoogleMap.MAP_TYPE_NORMAL, R.id.simpleMap),
    HYBRID(GoogleMap.MAP_TYPE_HYBRID, R.id.hybridMap),
    SATELLITE(GoogleMap.MAP_TYPE_SATELLITE, R.id.satelliteMap),
    TERRAIN(GoogleMap.MAP_TYPE_TERRAIN, R.id.terrainMap);

    private static final String TAG = MapType.class.getName();

    int androidMapTypeId;
    int menuItemMapTypeId;
    MapType(int androidMapTypeId, int menuItemMapTypeId) {
        this.androidMapTypeId = androidMapTypeId;
        this.menuItemMapTypeId = menuItemMapTypeId;
    }

    int getAndroidMapTypeId() {
        return androidMapTypeId;
    }

    int getMenuItemMapTypeId() {
        return menuItemMapTypeId;
    }

    public static MapType extractMapType(int androidMapTypeId) {
        for(MapType mapType : MapType.values()) {
            if(mapType.getAndroidMapTypeId() == androidMapTypeId) {
                return mapType;
            }
        }
        return MapType.SIMPLE;
    }

    public static MapType extractMapType(MenuItem item) {
        for(MapType mapType : MapType.values()) {
            if(mapType.getMenuItemMapTypeId() == item.getItemId()) {
                return mapType;
            }
        }
        return MapType.SIMPLE;
    }

    static public int extractAndroidMapType(MenuItem item) {
        MapType mapType = MapType.extractMapType(item);
        Log.i(TAG, "For menu item [name: " + item.getTitle() + "] extract mapType: [" + mapType.name() + "]");
        return mapType.getAndroidMapTypeId();
    }

    static public boolean contains(MenuItem item) {
        for(MapType mapTpe : MapType.values()) {
            if(mapTpe.getMenuItemMapTypeId() == item.getItemId()) {
                return true;
            }
        }
        return false;
    }

}
