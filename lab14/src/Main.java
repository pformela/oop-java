import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    public static void printOptions() {
        System.out.println();
        System.out.println("1. Add a sale offer for House.");
        System.out.println("2. Add a sale offer for Apartment.");
        System.out.println("3. Get house sale offers that start at a given date.");
        System.out.println("4. Get current sale offers for apartments.");
        System.out.println("5. Get current sale offers for houses in given city with area not lower than given value.");
        System.out.println("6. Get current sale offers for apartments in given city that are not more expensive than given value and from given floor number upwards.");
        System.out.println("7. Exit program.");
        System.out.print("Choose an option (1-7): ");
    }

    public static String getInput(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static int getOption(Scanner scanner) {
        int option;

        option = Integer.parseInt(scanner.nextLine());
        System.out.println();

        return option;
    }

    public static void printOffers(ArrayList<Property> offers) {
        System.out.println();
        for (int i = 0; i < offers.size(); i++)
            System.out.println((i + 1) + ". " + offers.get(i).toString());

        if (offers.size() == 0) {
            System.out.println("No offers for given filter.");
        }
    }


    public static void addHouseSaleOffer(SaleOffers saleOffers, Scanner scanner) throws Exception {
        System.out.println("Adding a house sale offer...");
        Pattern postalCodePattern = Pattern.compile("^\\d\\d-\\d\\d\\d$", Pattern.CASE_INSENSITIVE);

        String city = getInput(scanner, "Enter city: ");
        String street = getInput(scanner, "Enter street: ");
        String houseNumber = getInput(scanner, "Enter house number: ");
        String postalCode = getInput(scanner, "Enter postal code (XX-XXX format): ");

        if (!postalCodePattern.matcher(postalCode).find()) throw new Exception("Invalid postal code.");

        float floorSpace = Float.parseFloat(getInput(scanner, "Enter floor space: "));
        float plotArea = Float.parseFloat(getInput(scanner, "Enter plot area: "));
        float price = Float.parseFloat(getInput(scanner, "Enter price: "));
        String unformattedEndDate = getInput(scanner, "Enter start date (DD/MM/YYYY format): ");
        LocalDate startDate = LocalDate.parse(unformattedEndDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        House house = new House(city, street, houseNumber, postalCode, floorSpace, price, startDate, plotArea);

        saleOffers.addPropertySaleOffer(house);
        System.out.println("Sale offer for house added.\n");
    }

    public static void addApartmentSaleOffer(SaleOffers saleOffers, Scanner scanner) throws Exception {
        System.out.println("Adding an apartment sale offer...");
        Pattern postalCodePattern = Pattern.compile("^\\d\\d-\\d\\d\\d$", Pattern.CASE_INSENSITIVE);

        String city = getInput(scanner, "Enter city: ");
        String street = getInput(scanner, "Enter street: ");
        String houseNumber = getInput(scanner, "Enter house number: ");
        String postalCode = getInput(scanner, "Enter postal code (XX-XXX format): ");

        if (!postalCodePattern.matcher(postalCode).find()) throw new Exception("Invalid postal code.");

        float floorSpace = Float.parseFloat(getInput(scanner, "Enter floor space: "));
        int floorNumber = Integer.parseInt(getInput(scanner, "Enter floor number: "));
        int flatNumber = Integer.parseInt(getInput(scanner, "Enter flat number: "));
        float price = Float.parseFloat(getInput(scanner, "Enter price: "));
        String unformattedEndDate = getInput(scanner, "Enter start date (DD/MM/YYYY format): ");
        LocalDate startDate = LocalDate.parse(unformattedEndDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Apartment apartment = new Apartment(city, street, houseNumber, postalCode, floorSpace, price, startDate, flatNumber, floorNumber);

        saleOffers.addPropertySaleOffer(apartment);
        System.out.println("Sale offer for apartment added.\n");
    }

    public static void printHouseOffersThatStartAtGivenDate(SaleOffers saleOffers, Scanner scanner) throws Exception {
        String unformattedEndDate = getInput(scanner, "Enter start date (DD/MM/YYYY format): ");
        LocalDate startDate = LocalDate.parse(unformattedEndDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        ArrayList<Property> offers = saleOffers.filterOffers(offer -> offer instanceof House &&
                !offer.getStartDate().isBefore(startDate));

        System.out.println("\nShowing filtered offers for Houses:");
        printOffers(offers);
    }

    public static void printCurrentApartmentOffers(SaleOffers saleOffers) {
        LocalDate currentDate = LocalDate.now();

        ArrayList<Property> offers = saleOffers.filterOffers(offer -> offer instanceof Apartment &&
                !offer.getStartDate().isAfter(currentDate));

        System.out.println("\nShowing filtered offers for Apartments:");
        printOffers(offers);
    }

    public static void printCurrentHouseOffersWithCityAndArea(SaleOffers saleOffers, Scanner scanner) {
        LocalDate currentDate = LocalDate.now();
        String city = getInput(scanner, "Enter city: ");
        float floorArea = Float.parseFloat(getInput(scanner, "Enter floor area: "));

        ArrayList<Property> offers = saleOffers.filterOffers(offer -> offer instanceof House &&
                !offer.getStartDate().isAfter(currentDate) &&
                offer.getCity().equals(city) &&
                offer.getFloorSpace() >= floorArea);

        System.out.println("\nShowing filtered offers for Houses:");
        printOffers(offers);
    }

    public static void printCurrentApartmentOffersWithCityPriceAndFloor(SaleOffers saleOffers, Scanner scanner) {
        LocalDate currentDate = LocalDate.now();
        String city = getInput(scanner, "Enter city: ");
        float price = Float.parseFloat(getInput(scanner, "Enter price: "));
        int floorNumber = Integer.parseInt(getInput(scanner, "Enter floor number: "));

        ArrayList<Property> offers = saleOffers.filterOffers(offer -> offer instanceof Apartment &&
                !offer.getStartDate().isAfter(currentDate) &&
                offer.getFloorSpace() <= price &&
                offer.getCity().equals(city) &&
                ((Apartment) offer).getFloorNumber() >= floorNumber);

        System.out.println("\nShowing filtered offers for Apartments:");
        printOffers(offers);
    }


    public static boolean executeOptions(SaleOffers saleOffers, int option, Scanner scanner) throws Exception {
        switch (option) {
            case 1 -> addHouseSaleOffer(saleOffers, scanner);
            case 2 -> addApartmentSaleOffer(saleOffers, scanner);
            case 3 -> printHouseOffersThatStartAtGivenDate(saleOffers, scanner);
            case 4 -> printCurrentApartmentOffers(saleOffers);
            case 5 -> printCurrentHouseOffersWithCityAndArea(saleOffers, scanner);
            case 6 -> printCurrentApartmentOffersWithCityPriceAndFloor(saleOffers, scanner);
            case 7 -> {
                return false;
            }
            default -> System.out.println("Invalid option");
        }

        return true;
    }
    public static void main(String[] args) {
        System.out.println("***** Property sale offers *****");
        SaleOffers saleOffers = new SaleOffers();

        Scanner scanner = new Scanner(System.in);
        int option;
        boolean isRunning = true;

        while (isRunning) {
            try {
                printOptions();
                option = getOption(scanner);
                isRunning = executeOptions(saleOffers, option, scanner);

            } catch (Exception e) {
                System.out.println();
                System.out.println(e.getMessage());
            }
        }
    }
}