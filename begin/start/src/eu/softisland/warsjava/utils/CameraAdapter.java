package eu.softisland.warsjava.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;

public class CameraAdapter {

    private static CameraAdapter instance = null;

    private Camera cam;
    private boolean lightIsTurnOn = false;

    private CameraAdapter() {

    }

    public static synchronized CameraAdapter getInstance(){
        if(instance == null) {
            instance = new CameraAdapter();
        }
        return instance;
    }

    public boolean hasFlash(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    public synchronized void init() {
//        if(cam == null) {
            cam = Camera.open();
            Camera.Parameters p = cam.getParameters();
            p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            cam.setParameters(p);
//        }
    }

    public void turnOnLight() {
        init();
        cam.startPreview();
        lightIsTurnOn = true;
    }

    public void turnOfLight() {
        cam.stopPreview();
        lightIsTurnOn = false;
        release();
    }

    public boolean isLightIsTurnOn() {
        return lightIsTurnOn;
    }

    public void release() {
        if(cam != null) {
            cam.release();
        }
    }

}
