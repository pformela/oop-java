import java.time.LocalDate;

final public class House extends Property {

    private float plotArea;

    public House(String city,
                 String street,
                 String houseNumber,
                 String postalCode,
                 float floorSpace,
                 float price,
                 LocalDate startDate,
                 float plotArea) {
        super(city, street, houseNumber, postalCode, floorSpace, price, startDate);
        this.plotArea = plotArea;
    }

    public float getPlotArea() { return this.plotArea; }

    public void setPlotArea(float newPlotArea) {
        this.plotArea = newPlotArea;
    }
    public String toString() {
        return "HOUSE >>> " +
                " City: " + this.getCity() +
                ", Street: " + this.getStreet() +
                ", House Number: " + this.getHouseNumber() +
                ", Plot Area: " + this.getPlotArea() + " m2" +
                ", Postal Code: " + this.getPostalCode() +
                ", Floor Space: " + this.getFloorSpace() +
                ", Price: " + this.getPrice() +
                ", Start Date: " + this.getStartDate();
    }
}
