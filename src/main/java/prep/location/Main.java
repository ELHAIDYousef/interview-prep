package prep.location;

import java.util.List;

/**
 *
 * @author Yousef ELHAID
 */
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
    }
}
