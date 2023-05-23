import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

public class SaleOffers {
    private ArrayList<Property> offerList;
    public SaleOffers() {
        this.offerList = new ArrayList<>();
    }

    public ArrayList<Property> filterOffers(Predicate<Property> predicate) {
        ArrayList<Property> filteredOffers = new ArrayList<>();

        for (Property offer : offerList) {
            if (predicate.test(offer))
                filteredOffers.add(offer);
        }

        return filteredOffers;
    }

    public void addPropertySaleOffer(House house) {
        this.offerList.add(house);
    }

    public void addPropertySaleOffer(Apartment apartment) {
        this.offerList.add(apartment);
    }
}
