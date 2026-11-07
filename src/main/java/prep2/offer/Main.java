package prep2.offer;

import java.util.*;

abstract class Offer {

    private static int created;
    private static final Map<String, Integer> BASE_RATES = Map.of("ECO", 250, "SUV", 600);

    private final int id;
    private final String category;

    public Offer(String category) {
        Objects.requireNonNull(category, "Category");
        String normalizedCategory = category.trim().toUpperCase(Locale.ROOT);
        if (!BASE_RATES.containsKey(normalizedCategory)) {
            throw new IllegalArgumentException("Category does not exist.");
        }
        this.category = normalizedCategory;
        created++;
        this.id = created;
    }

    protected int baseRate() { // T O(1) M O(1)
        return BASE_RATES.get(category);
    }

    static String label() {  // T O(1) M O(1)
        return "Standard";
    }

    public int getId() {  // T O(1) M O(1)
        return id;
    }

    public String getCategory() {  // T O(1) M O(1)
        return category;
    }

    static int created() {  // T O(1) M O(1)
        return created;
    }

    public static Offer cheapest(List<Offer> offerList) {  // T O(n) M O(1)
        if (offerList.isEmpty()) {
            return null;
        }
        Offer cheapest = null;
        for (Offer offer : offerList) {
            if (cheapest == null || offer.dailyPrice() < cheapest.dailyPrice()) {
                cheapest = offer;
            }
        }
        return cheapest;
    }


    abstract double dailyPrice();

    abstract Offer renew();
}

class StandardOffer extends Offer {
    public StandardOffer(String category) {
        super(category);
    }

    @Override
    double dailyPrice() {  // T O(1) M O(1)
        return baseRate();
    }

    @Override
    StandardOffer renew() {  // T O(1) M O(1)
        return new StandardOffer(getCategory());
    }
}

class WeekendOffer extends Offer {
    private static final double DISCOUNT = 0.2;

    public WeekendOffer(String category) {
        super(category);
    }

    static String label() {  // T O(1) M O(1)
        return "Week-end";
    }

    @Override
    double dailyPrice() {  // T O(1) M O(1)
        int price = baseRate();
        return price - price * DISCOUNT;
    }

    @Override
    WeekendOffer renew() {  // T O(1) M O(1)
        return new WeekendOffer(getCategory());
    }
}

public class Main {

    public static void main(String[] args) {
        Offer a = new StandardOffer("SUV");
        Offer b = new WeekendOffer("SUV");
        Offer c = b.renew();
        WeekendOffer w = new WeekendOffer("ECO");
        WeekendOffer w2 = w.renew();

        System.out.println(c.getClass().getSimpleName() + " #" + c.getId() + " " + c.dailyPrice());
        System.out.println(Offer.cheapest(List.of(a, b, c)).getId());
        System.out.println(Offer.cheapest(List.of()));
        System.out.println(w2.getId() + " " + w2.dailyPrice());
        System.out.println(Offer.created());
        System.out.println(b.label());

        try {
            new StandardOffer("LUXE");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Offer.created());
    }
}