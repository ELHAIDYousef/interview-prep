package prep.records;

import java.util.*;
import java.util.stream.*;

/**
 *
 * @author Yousef ELHAID
 */
public class Main3 {

    public static void main(String[] args) {
        List<Integer> fixed = Arrays.asList(3, 1, 2);
        fixed.set(0, 9);
        System.out.println(fixed);                                        // 1
        try {
            fixed.add(4);
        } catch (UnsupportedOperationException e) {
            System.out.println("asList : taille fixe");                   // 2
        }

        Integer[] array = {3, 1, 2};
        List<Integer> view = Arrays.asList(array);
        array[0] = 7;
        System.out.println(view);                                         // 3

        try {
            System.out.println(List.of(1, 2, 3).contains(null));
        } catch (NullPointerException e) {
            System.out.println("List.of refuse null");                    // 4
        }

        List<String> names = new ArrayList<>(List.of("Ali", "Sara", "Omar", "Ali"));
        System.out.println(Collections.frequency(names, "Ali"));          // 5
        names.removeIf(name -> name.startsWith("A"));
        System.out.println(names);                                        // 6

        List<Integer> values = new ArrayList<>(List.of(1, 2, 3, 4));
        try {
            for (Integer value : values) {
                if (value == 1) {
                    values.remove(value);
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("CME");                                    // 7
        }
        System.out.println(values);                                       // 8

        Map<String, Integer> stock = new HashMap<>();
        stock.merge("pomme", 3, Integer::sum);
        stock.merge("pomme", 2, Integer::sum);
        System.out.println(stock.getOrDefault("pomme", 0) + " " + stock.getOrDefault("kiwi", 0)
                + " " + stock.containsKey("kiwi"));                       // 9

        Map<Integer, List<String>> wordsByLength = new TreeMap<>();
        for (String word : List.of("java", "ok", "list", "go")) {
            wordsByLength.computeIfAbsent(word.length(), length -> new ArrayList<>()).add(word);
        }
        System.out.println(wordsByLength);                                // 10

        Optional<String> firstA = Stream.of("kiwi", "banane", "avocat", "ananas")
                .filter(fruit -> fruit.startsWith("a"))
                .findFirst();
        System.out.println(firstA.map(String::toUpperCase).orElse("aucun")); // 11

        String nickname = null;
        System.out.println(Objects.requireNonNullElse(nickname, "anonyme"));  // 12

        Integer a = 127;
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;
        System.out.println((a == b) + " " + (c == d) + " " + c.equals(d));   // 13

        int[] numbers = {5, 3, 9, 1};
        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers) + " " + Arrays.binarySearch(numbers, 5)); // 14
    }
}