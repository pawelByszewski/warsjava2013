package eu.softisland.warsjava.model.car;

import eu.softisland.warsjava.model.car.components.Engine;
import eu.softisland.warsjava.model.car.components.LeatherUpholstery;
import eu.softisland.warsjava.model.car.components.Transmission;
import eu.softisland.warsjava.model.car.components.VeloursUpholstery;

public class CarFactory {

    public static Car createCar(CarType carType) {
        Car car = new Car();
        car.setEngine(new Engine());
        car.setTransmission(new Transmission());
        if(carType == CarType.PREMIUM) {
            car.setUpholstery(new LeatherUpholstery());
        } else {
            car.setUpholstery(new VeloursUpholstery());
        }

        return car;
    }
}
