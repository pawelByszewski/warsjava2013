package eu.softisland.warsjava.model.car;

import android.util.Log;
import eu.softisland.warsjava.model.car.components.Engine;
import eu.softisland.warsjava.model.car.components.Transmission;
import eu.softisland.warsjava.model.car.components.Upholstery;

public class Car {

   private static final String TAG = Car.class.getName();

    private Transmission transmission;
    private Engine engine;
    private Upholstery upholstery;

    static int count = 0;

    public Car() {
        Log.i(TAG, "create car, count : " + count);
        ++count;
    }

    @Override
    public String toString() {
        return "Car{" +
                "transmission=" + transmission +
                ", engine=" + engine +
                ", upholstery=" + upholstery +
                '}';
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setUpholstery(Upholstery upholstery) {
        this.upholstery = upholstery;
    }
}
