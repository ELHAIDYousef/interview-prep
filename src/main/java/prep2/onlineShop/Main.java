package prep2.onlineShop;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Yousef ELHAID
 */

final class Product {

    private final String sku;
    private final String name;
    private final BigDecimal price;

    public Product(String sku, String name, BigDecimal price) {
        Objects.requireNonNull(sku, "sku");
        Objects.requireNonNull(name, "name");
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must not be negative:" + price);
        }
        String cleanName = name.trim();
        if (cleanName.isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        String cleanSKU = sku.trim().toUpperCase(Locale.ROOT);
        if (cleanSKU.isEmpty()) {
            throw new IllegalArgumentException("SKU must not be empty");
        }
        this.sku = cleanSKU;
        this.name = cleanName;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Product other)) {
            return false;
        }
        return this.sku.equals(other.sku);
    }

    @Override
    public int hashCode() {
        return this.sku.hashCode();
    }

    @Override
    public String toString() {
        return sku + " " + name + " " + price;
    }
}

class Cart {
    Map<Product, Integer> quantities = new LinkedHashMap<>();

    public int add(Product product, int quantity) {
        Objects.requireNonNull(product, "product");
        requirePositiveQuantity(quantity);
        return quantities.merge(product, quantity, Integer::sum);
    }

    public void remove(Product product, int quantity) {
        Objects.requireNonNull(product, "product");
        requirePositiveQuantity(quantity);
        int current = quantityOf(product);
        if (current == 0) {
            throw new IllegalArgumentException("Product not in cart: " + product.getSku());
        }
        if (quantity > current) {
            throw new IllegalStateException(
                    "Cannot remove " + quantity + " x " + product.getSku() + ": only " + current + " in cart");
        }
        if (quantity == current) {
            quantities.remove(product);
        } else {
            quantities.put(product, current - quantity);
        }
    }

    public int quantityOf(Product product) {
        Objects.requireNonNull(product, "product");
        return quantities.getOrDefault(product,0);
    }

    public int totalItems() {
        int total = 0;
        for (int quantity : quantities.values()) {
            total += quantity;
        }
        return total;
    }

    public BigDecimal total() {
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : quantities.entrySet()) {
            BigDecimal lineTotal = entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue()));
            total = total.add(lineTotal);
        }
        return total;
    }

    private void requirePositiveQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must not be negative:" + quantity);
        }
    }

    @Override
    public String toString() {
        return quantities.entrySet().stream()
                .map(entry -> entry.getKey().getSku() + " x" + entry.getValue())
                .collect(Collectors.joining(", ", "[", "]"));
    }

}

public class Main {

    public static void main(String[] args) {
        Product pen = new Product("pen-1", "Pen", new BigDecimal("1.50"));
        Product book = new Product("BOOK-7", "Clean Code", new BigDecimal("35.90"));
        Product penAgain = new Product(" PEN-1 ", "Blue pen", new BigDecimal("2.00"));

        Cart cart = new Cart();
        cart.add(pen, 2);
        cart.add(book, 1);
        cart.add(penAgain, 3);

        System.out.println(cart);
        System.out.println(cart.quantityOf(pen));
        System.out.println(cart.totalItems());
        System.out.println(cart.total());

        cart.remove(pen, 5);
        System.out.println(cart.quantityOf(pen));
        System.out.println(cart);

        try {
            cart.add(book, 0);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        try {
            cart.remove(book, 2);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(new Cart().total());
    }
}