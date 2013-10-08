package eu.softisland.warsjava.view.car;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import com.google.analytics.tracking.android.EasyTracker;
import eu.softisland.warsjava.R;
import eu.softisland.warsjava.model.car.Car;
import eu.softisland.warsjava.model.car.CarFactory;
import eu.softisland.warsjava.model.car.CarType;

public class CarActivity extends Activity {

    private Car premiumCar;
    private Car standardCar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_legs);
        premiumCar = CarFactory.createCar(CarType.PREMIUM);
        standardCar = CarFactory.createCar(CarType.STANDARD);
    }

    public void chosenPremiumCar(View view) {
        showPickedItem(premiumCar);
    }

    public void chosenStandardCar(View view) {
        showPickedItem(standardCar);
    }

    private void showPickedItem(Car car) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(car.toString()).setTitle("You picked:");
        builder.create().show();
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
