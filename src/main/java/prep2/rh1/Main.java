package prep2.rh1;

import java.util.*;

/**
 *
 * @author Yousef ELHAID
 */
record Person(String name, int age) implements Comparable<Person>{
    public Person {
        Objects.requireNonNull(name, "name");
        name = name.trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be blank");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Aga must not be negative: " + age);
        }
    }

    @Override
    public int compareTo(Person person) {
        int byAge = Integer.compare(age, person.age);
        if (byAge != 0) {
            return byAge;
        }
        return name.compareTo(person.name);
    }
}

final class PeopleSorter {
    private PeopleSorter() {}

    public static List<Person> byAgeDescending(List<Person> people) {
        return people.stream()
                .sorted(Comparator.comparingInt(Person::age).reversed()
                        .thenComparing(Person::name))
                .toList();
    }

    public static Person youngest(List<Person> people) {
        Person youngest = null;
        for (Person person : people) {
            if (youngest == null || person.age() < youngest.age()) {
                youngest = person;
            }
        }
        return youngest;
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
        System.out.println(new TreeSet<>(people));
    }
}