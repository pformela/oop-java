import java.time.LocalDate;

final public class Apartment extends Property {

    private int flatNumber;
    private int floorNumber;

    public Apartment(String city,
                     String street,
                     String houseNumber,
                     String postalCode,
                     double floorSpace,
                     double price,
                     LocalDate startDate,
                     int flatNumber,
                     int floorNumber) {
        super(city, street, houseNumber, postalCode, floorSpace, price, startDate);
        this.flatNumber = flatNumber;
        this.floorNumber = floorNumber;
    }

    public int getFlatNumber() { return this.flatNumber; }

    public int getFloorNumber() { return this.floorNumber; }

    public void setFlatNumber(int newFlatNumber) {
        this.flatNumber = newFlatNumber;
    }

    public void setFloorNumber(int newFloorNumber) {
        this.floorNumber = newFloorNumber;
    }
    public String toString() {
        return "APARTMENT >>> " +
                " City: " + this.getCity() +
                ", Street: " + this.getStreet() +
                ", House Number: " + this.getHouseNumber() +
                ", Floor Number: " + this.getFloorNumber() +
                ", Flat Number: " + this.getFlatNumber() +
                ", Postal Code: " + this.getPostalCode() +
                ", Floor Space: " + this.getFloorSpace() + " m2" +
                ", Price: " + this.getPrice() +
                ", Start Date: " + this.getStartDate();
    }
}
