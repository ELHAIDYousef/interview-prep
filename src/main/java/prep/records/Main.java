package prep.records;

import java.util.*;

/**
 *
 * @author Yousef ELHAID
 */
record Person(String name, int age) {

    Person {
        Objects.requireNonNull(name, "name");
        name = name.trim();
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name must not be blank");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Age must not be negative: " + age);
        }
    }
}

final class PeopleSorter {

    private static final Comparator<Person> BY_AGE_DESCENDING_THEN_NAME =
            Comparator.comparingInt(Person::age).reversed().thenComparing(Person::name);

    private PeopleSorter() {
    }

    static List<Person> byAgeDescending(List<Person> people) { // T O(n log n) M O(n)
        return people.stream()
                .sorted(BY_AGE_DESCENDING_THEN_NAME)
                .toList();
    }

    static Person youngest(List<Person> people) { // T O(n) M O(1)
        return Collections.min(people, Comparator.comparingInt(Person::age));
    }
}

public class Main {

    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person(" Sara ", 25),
                new Person("Ali", 30),
                new Person("Omar", 25),
                new Person("Nadia", 41));

        System.out.println(PeopleSorter.byAgeDescending(people));
        System.out.println(people.get(0));
        System.out.println(new Person("Ali", 30).equals(people.get(1)));
        System.out.println(new Person("Ali", 30) == people.get(1));
        System.out.println(PeopleSorter.youngest(people));
        try {
            new Person("Zak", -1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            people.sort(Comparator.comparingInt(Person::age));
        } catch (UnsupportedOperationException e) {
            System.out.println("Liste non modifiable");
        }
    }
}
