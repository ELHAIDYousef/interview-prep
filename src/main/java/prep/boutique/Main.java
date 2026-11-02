package prep.boutique;

import java.util.*;

/**
 *
 * @author Yousef ELHAID
 */
interface CatalogItem {

    String getName();

    long stockValue();

    int productCount();

    List<String> outOfStock();

    boolean contains(CatalogItem item);   // bonus h) : this, ou un de ses descendants, est-il item ?
}

final class Product implements CatalogItem {
    private final String name;
    private final long unitPrice;
    private final int quantity;

    Product(String name, long unitPrice, int quantity) {
        checkNotNegative(unitPrice, quantity);
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long stockValue() { // T O(1) M O(1)
        return unitPrice * quantity;
    }

    @Override
    public int productCount() {
        return 1;
    }

    @Override
    public List<String> outOfStock() {
        return quantity == 0 ? List.of(name) : List.of();             // ← jamais null
    }

    @Override
    public boolean contains(CatalogItem item) {
        return this == item;                                         // ← l'identité, explicitement
    }

    private static void checkNotNegative(long unitPrice, int quantity) {
        if (unitPrice < 0) {                                         // ← 0 est autorisé (règle f)
            throw new IllegalArgumentException("Price must not be negative: " + unitPrice);
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must not be negative: " + quantity);
        }
    }
}

final class Category implements CatalogItem {
    private final String name;
    private final List<CatalogItem> children = new ArrayList<>();

    Category(String name) {
        this.name = name;
    }

    void add(CatalogItem child) {
        if (child.contains(this)) {                                  // ← le bonus, branché
            throw new IllegalArgumentException(
                    "Adding " + child.getName() + " to " + name + " would create a cycle");
        }
        children.add(child);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long stockValue() { // T O(n) M O(h)
        long total = 0;
        for (CatalogItem child : children) {
            total += child.stockValue();
        }
        return total;
    }

    @Override
    public int productCount() { // T O(n) M O(h)
        int count = 0;
        for (CatalogItem child : children) {
            count += child.productCount();
        }
        return count;
    }

    @Override
    public List<String> outOfStock() { // T O(n × h) M O(n)
        List<String> names = new ArrayList<>();
        for (CatalogItem child : children) {
            names.addAll(child.outOfStock());                        // ← UN seul appel, pas de null
        }
        return names;
    }

    @Override
    public boolean contains(CatalogItem item) { // T O(n) M O(h)
        if (this == item) {                                          // ← se tester soi-même d'abord
            return true;
        }
        for (CatalogItem child : children) {
            if (child.contains(item)) {
                return true;
            }
        }
        return false;
    }
}

public class Main {

    public static void main(String[] args) {
        Category catalog = new Category("Catalogue");
        Category computing = new Category("Informatique");
        Category accessories = new Category("Accessoires");
        Category office = new Category("Bureau");
        Category promotions = new Category("Promotions");

        computing.add(new Product("Laptop", 8000, 3));
        computing.add(new Product("Souris", 150, 0));
        accessories.add(new Product("Cable USB", 50, 100));
        computing.add(accessories);
        office.add(new Product("Chaise", 1200, 5));
        office.add(new Product("Lampe", 300, 0));
        catalog.add(computing);
        catalog.add(office);
        catalog.add(promotions);

        System.out.println(catalog.stockValue());
        System.out.println(catalog.productCount());
        System.out.println(catalog.outOfStock());
        System.out.println(computing.stockValue());
        System.out.println(promotions.stockValue() + " " + promotions.productCount());

        CatalogItem keyboard = new Product("Clavier", 400, 2);
        System.out.println(keyboard.stockValue() + " " + keyboard.productCount() + " " + keyboard.outOfStock());

        try {                                    // bonus h)
            accessories.add(catalog);
        } catch (IllegalArgumentException e) {
            System.out.println("Cycle refusé");
        }
    }
}
