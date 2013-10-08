package eu.softisland.warsjava.model.car.components;

public class VeloursUpholstery implements Upholstery {

    @Override
    public String getDescription() {
        return "Velours upholstery";
    }

    public String toString() {
        return getDescription();
    }
}
