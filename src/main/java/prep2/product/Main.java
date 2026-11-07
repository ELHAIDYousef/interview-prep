package prep2.product;

import java.util.*;

interface CatalogItem {

    String getName();

    long stockValue();

    int productCount();

    List<String> outOfStock();

    boolean contains(CatalogItem item);  
}

final class Product implements CatalogItem {
    private final String name;
    private final long unitPrice;
    private final int quantity;

    Product(String name, long unitPrice, int quantity) {
        this.name = Objects.requireNonNull(name, "name");
        if (unitPrice < 0 ) {
            throw new IllegalArgumentException("Price must not be negative: " + unitPrice);
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must not be negative: " + quantity);
        }
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    @Override
    public String getName() { // T O(1) M O(1)
        return name;
    }

    @Override
    public long stockValue() { // T O(1) M O(1)
        return quantity * unitPrice;
    }

    @Override
    public int productCount() { // T O(1) M O(1)
        return 1;
    }

    @Override
    public List<String> outOfStock() { // T O(1) M O(1)
        if (quantity == 0) {
            return List.of(name);
        }
        return List.of();
    }

    @Override
    public boolean contains(CatalogItem item) {
        return this == item;
    }
}

final class Category implements CatalogItem {
    private final String name;
    private final List<CatalogItem> children = new ArrayList<>();

    Category(String name) {
        this.name = name;
    }

    void add(CatalogItem child) {
        if (child.contains(this)) {
            throw new IllegalArgumentException(
                    "Adding " + child.getName() + " to " + name + " would create a cycle");
        }
        children.add(child);
    }

    @Override
    public String getName() { // T O(1) M O(1)
        return name;
    }

    @Override
    public long stockValue() { // T O(n) M O(1)
        long total = 0;
        for (CatalogItem child : children) {
            total += child.stockValue();
        }
        return total;
    }

    @Override
    public int productCount() { // T O(n) M O(1)
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
            names.addAll(child.outOfStock());
        }
        return names;
    }

    @Override
    public boolean contains(CatalogItem item) { // T O(n) M O(1)
        if (this == item) {
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

        System.out.println(new Product("Cadeau", 0, 5).stockValue());
        try {
            new Product("Erreur", -1, 1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            accessories.add(catalog);
        } catch (IllegalArgumentException e) {
            System.out.println("Cycle refusé");
        }
    }
}