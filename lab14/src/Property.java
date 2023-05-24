import java.time.LocalDate;
import java.time.LocalTime;

sealed public abstract class Property permits House, Apartment {
    private String city;
    private String street;
    private String houseNumber;
    private String postalCode;
    private double floorSpace;
    private double price;
    private LocalDate startDate;

    public Property(String city,
                    String street,
                    String houseNumber,
                    String postalCode,
                    double floorSpace,
                    double price,
                    LocalDate startDate) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.floorSpace = floorSpace;
        this.price = price;
        this.startDate = startDate;
    }

    public String getCity() {
        return this.city;
    }

    public String getStreet() {
        return this.street;
    }

    public String getHouseNumber() {
        return this.houseNumber;
    }

    public String getPostalCode() { return this.postalCode; }

    public double getFloorSpace() { return this.floorSpace; }

    public double getPrice() { return this.price; }

    public LocalDate getStartDate() { return this.startDate; }

    public void setCity(String newCity){
        this.city = newCity;
    }

    public void setStreet(String newStreet) {
        this.street = newStreet;
    }

    public void setHouseNumber(String newHouseNumber) {
        this.houseNumber = newHouseNumber;
    }

    public void setPostalCode(String newPostalCode) {
        this.postalCode = newPostalCode;
    }

    public void setFloorSpace(float newFloorSpace) {
        this.floorSpace = newFloorSpace;
    }

    public void setPrice(float newPrice) {
        this.price = newPrice;
    }

    public void setStartDate(LocalDate newStartDate) {
        this.startDate = newStartDate;
    }

    public String toString() {
        return "PROPERTY >>> " +
                " City: " + this.getCity() +
                ", Street: " + this.getStreet() +
                ", House Number: " + this.getHouseNumber() +
                ", Postal Code: " + this.getPostalCode() +
                ", Floor Space: " + this.getFloorSpace() +
                ", Price: " + this.getPrice() +
                ", Start Date: " + this.getStartDate();
    }

}
