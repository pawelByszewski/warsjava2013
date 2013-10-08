package eu.softisland.warsjava.map;

import android.util.Log;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapAdapter {

    private static final String TAG = MapAdapter.class.getName();

    private final GoogleMap googleMap;

    public MapAdapter(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    public void setLandmarkLocation(LatLng landmarkLocation) {
        Log.i(TAG, "Set mock location: [" + landmarkLocation.toString() + "]");
        moveMapTo(landmarkLocation);
        MarkerOptions markerOptions = new MarkerOptions().position(landmarkLocation);
        googleMap.addMarker(markerOptions);
    }

    private void moveMapTo(LatLng addressLocation) {
        CameraPosition currentPosition = googleMap.getCameraPosition();
        CameraPosition cameraPosition = CameraPosition.builder().target(addressLocation)
                .bearing(currentPosition.bearing)
                .zoom(currentPosition.zoom)
                .tilt(currentPosition.tilt)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        googleMap.animateCamera(cameraUpdate);
    }

    public void setMapType(int mapType) {
        googleMap.setMapType(mapType);
    }
}
