package prep2.boutique;

import java.util.*;

record OrderLine(String product, int quantity) {
}

record Order(int id, String customer, List<OrderLine> lines) {

    Order {
        lines = List.copyOf(lines);
    }
}

final class OrderBook {
    private final List<Order> orders = new ArrayList<>();

    void add(Order order) {
        orders.add(order);
    }

    Map<String, Integer> quantityByProduct() {
        Map<String, Integer> products = new LinkedHashMap<>();
        for (Order order : orders) {
            for (OrderLine line : order.lines()) {
                products.merge(line.product(), line.quantity(), Integer::sum);
            }
        }
        return products;
    }

    List<Order> newestFirst() {
        List<Order> result = new LinkedList<>();
        for (Order order : orders) {
            result.addFirst(order);
        }
        return List.copyOf(result);
    }

    List<String> customersInOrderOfFirstPurchase() {
        List<String> customers = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        for (Order order : orders) {
            if (!seen.contains(order.customer())) {
                customers.add(order.customer());
                seen.add(order.customer());
            }
        }
        return customers;
    }

    List<String> topProducts(int k) { // T O(L + p log p) M O(p)
        return quantityByProduct().entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                .thenComparing(Map.Entry.comparingByValue()))
                .limit(k)
                .map(Map.Entry::getKey)
                .toList();
    }
}

public class Main {

    public static void main(String[] args) {
        OrderBook book = new OrderBook();
        book.add(new Order(1, "Sara", List.of(new OrderLine("Laptop", 1), new OrderLine("Souris", 2))));
        book.add(new Order(2, "Ali", List.of(new OrderLine("Souris", 1))));
        book.add(new Order(3, "Sara", List.of(new OrderLine("Clavier", 3), new OrderLine("Laptop", 1))));
        book.add(new Order(4, "Omar", List.of(new OrderLine("Souris", 2))));
        book.add(new Order(5, "Ali", List.of(new OrderLine("Ecran", 3))));

        System.out.println(book.quantityByProduct());
        System.out.println(book.newestFirst().stream().map(Order::id).toList());
        System.out.println(book.customersInOrderOfFirstPurchase());
        System.out.println(book.topProducts(2));
        System.out.println(book.topProducts(10).size());
        try {
            book.newestFirst().add(new Order(6, "Zak", List.of()));
        } catch (UnsupportedOperationException e) {
            System.out.println("Lecture seule");
        }
        System.out.println(book.newestFirst().size());
    }
}
