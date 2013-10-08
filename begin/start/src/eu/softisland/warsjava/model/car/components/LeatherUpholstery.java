package eu.softisland.warsjava.model.car.components;

public class LeatherUpholstery implements Upholstery {


    @Override
    public String getDescription() {
        return "Leather upholstery";
    }

    public String toString() {
        return getDescription();
    }
}
