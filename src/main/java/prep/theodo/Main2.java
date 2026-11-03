package prep.theodo;
/*
 * Moteur de tarification d'une plateforme de location de voitures.
 *
 * a) Chaque offre reçoit un id unique à sa création : 1, 2, 3...
 *
 * b) Les tarifs journaliers de base sont chargés une seule fois,
 *    au chargement de la classe :
 *      ECO = 250
 *      SUV = 600
 *
 * c) StandardOffer :
 *      Prix journalier = tarif de base de sa catégorie.
 *
 *    WeekendOffer :
 *      20 % de remise sur le tarif de base.
 *
 * d) renew() renvoie une nouvelle offre de même type
 *    et de même catégorie.
 *
 *    Pour une WeekendOffer, l'appelant écrit :
 *      WeekendOffer w2 = w.renew();
 *
 * e) Offer.cheapest(List<Offer>) renvoie l'offre la moins chère.
 *    En cas d'égalité, la première de la liste.
 *    null si la liste est vide.
 *
 * f) Offer.created() renvoie le nombre d'offres créées.
 *
 * g) Donner ce qu'affiche main.
 */

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

abstract class Offer {
    private static final Map<String,Integer> TARIF_JOURNALIER;

    static {
        Map<String, Integer> tarifInitialisateur = new HashMap<>();
        tarifInitialisateur.put("SUV", 600);
        tarifInitialisateur.put("ECO", 250);
        TARIF_JOURNALIER = Collections.unmodifiableMap(tarifInitialisateur);
    }

    protected int id;
    protected String categorie;

    public Offer(String categorie) {
        if (!(TARIF_JOURNALIER.containsKey(categorie))){
            throw new IllegalArgumentException("This category does not exist!!!");
        }

        this.categorie = categorie;
        this.id = IdGenerator.getInstance().nextId();
    }

    public static int created() {
        return IdGenerator.getInstance().created();
    }

    public int getId() {
        return id;
    }

    public String getCategorie() {
        return categorie;
    }

    public double dailyPrice() {
        return TARIF_JOURNALIER.get(categorie);
    }

    static String label() {
        return "Standard";
    }

    public static Integer cheapest(List<Offer> offers) {
        Offer cheapest = null;
        for (Offer offer : offers) {
            if (cheapest == null || offer.dailyPrice() < cheapest.dailyPrice()) {
                cheapest = offer;
            }
        }
        return cheapest != null ? cheapest.getId() : null;
    }


    abstract Offer renew();
}

class StandardOffer extends Offer {

    public StandardOffer(String categorie) {
        super(categorie);
    }

    @Override
    Offer renew() {
        return new StandardOffer(categorie);
    }
}

class WeekendOffer extends Offer {


    public WeekendOffer(String categorie) {
        super(categorie);
    }

    @Override
    WeekendOffer renew() {
        return new WeekendOffer(categorie);
    }

    public double dailyPrice() {
        double price = super.dailyPrice();
        return price * 0.8;
    }


    static String label() {
        return "Week-end";
    }
}

final class IdGenerator {
    private final AtomicInteger created = new AtomicInteger();

    private IdGenerator() {}

    private static class Holder {
        private static final IdGenerator INSTANCE = new IdGenerator();
    }

    static IdGenerator getInstance() {
        return Holder.INSTANCE;
    }

    public int nextId() {
        return created.incrementAndGet();
    }

    public int created() {
        return created.get();
    }

}


public class Main2 {

    public static void main(String[] args) {

        Offer a = new StandardOffer("SUV");
        Offer b = new WeekendOffer("SUV");
        Offer c = b.renew();

        WeekendOffer w = new WeekendOffer("ECO");
        WeekendOffer w2 = w.renew();

        System.out.println(
                c.getClass().getSimpleName()
                        + " #" + c.getId()
                        + " " + c.dailyPrice()
        );

        System.out.println(Offer.cheapest(List.of(a, b, c)));
        System.out.println(Offer.cheapest(List.of()));

        System.out.println(w2.getId() + " " + w2.dailyPrice());

        System.out.println(Offer.created());

        System.out.println(b.label());
    }

    /*
     * Résultat attendu :
     *
     * WeekendOffer #3 480.0
     * 2
     * null
     * 5 200.0
     * 5
     * Standard
     */
}
