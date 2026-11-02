package prep.location;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yousef ELHAID
 */
abstract class Offer {

    private static final Map<String, Integer> BASE_DAILY_RATES;     // ← private, en anglais
    private static int createdCount;                                // ← private : personne ne peut le remettre à 0

    static {
        Map<String, Integer> rates = new HashMap<>();               // ← un nom qui dit ce que c'est
        rates.put("ECO", 250);
        rates.put("SUV", 600);
        BASE_DAILY_RATES = Collections.unmodifiableMap(rates);
    }

    private final int id;                                           // ← private : les getters suffisent
    private final String category;

    protected Offer(String category) {                              // ← protected : seules les sous-classes l'appellent
        if (!BASE_DAILY_RATES.containsKey(category)) {              // ← échouer tôt
            throw new IllegalArgumentException("Unknown category: " + category);
        }
        this.category = category;
        createdCount++;                                             // ← 2 lignes, pas de ++ caché
        this.id = createdCount;
    }

    int getId() {
        return id;
    }

    String getCategory() {
        return category;
    }

    protected int baseDailyRate() {
        return BASE_DAILY_RATES.get(category);
    }

    abstract double dailyPrice();

    abstract Offer renew();

    static Offer cheapest(List<Offer> offers) {
        Offer cheapestOffer = null;
        for (Offer offer : offers) {
            if (cheapestOffer == null || offer.dailyPrice() < cheapestOffer.dailyPrice()) {
                cheapestOffer = offer;
            }
        }
        return cheapestOffer;
    }

    static int created() {
        return createdCount;
    }

    static String label() {
        return "Standard";
    }
}