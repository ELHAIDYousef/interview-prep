package prep2.car;

import java.util.*;

final class Car /* implements Comparable<Car> */{
    private final String registrationNumber;
    private final String brand;
    private int mileage;

    Car(String registrationNumber, String brand, int mileage) {
        this.brand = brand;
        this.mileage = mileage;
        Objects.requireNonNull(registrationNumber, "registrationNumber");
        String normalizedRegistrationNumber = registrationNumber.trim().toUpperCase();
        if (normalizedRegistrationNumber.isEmpty()) {
            throw new IllegalArgumentException("normalizedRegistrationNumber should be empty!!!");
        }
        this.registrationNumber = normalizedRegistrationNumber;
    }

    String getRegistrationNumber() {
        return registrationNumber;
    }

    String getBrand() {
        return brand;
    }

    int getMileage() {
        return mileage;
    }

    void drive(int kilometers) {
        mileage += kilometers;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Car other)) {
            return false;
        }
        return this.registrationNumber.equals(other.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(registrationNumber);
    }

    @Override
    public String toString() {
        return registrationNumber + " (" + brand + ", " + mileage + " km)";
    }
//
//    @Override
//    public int compareTo(Car car) {
//        int byMileage = Integer.compare(this.mileage, car.getMileage());
//        if (byMileage != 0) {
//            return byMileage;
//        }
//        return this.registrationNumber.compareTo(car.getRegistrationNumber());
//    }
}

final class Fleet {
    private final Set<Car> cars = new HashSet<>();

    boolean addCar(Car car) {
        return cars.add(car);
    }

    boolean contains(Car car) {
        return cars.contains(car);
    }

    int size() {
        return cars.size();
    }

    Map<String, Integer> countByBrand() {
        Map<String, Integer> countMap = new HashMap<>();
        for (Car car : cars) {
            countMap.merge(car.getBrand(), 1, Integer::sum);
        }
        return countMap;
    }

    List<Car> sortedByMileage() {
        return cars.stream()
                .sorted(Comparator.comparingInt(Car::getMileage)
                        .thenComparing(Car::getRegistrationNumber))
                .toList();
    }
}

public class Main {

    public static void main(String[] args) {
        Fleet fleet = new Fleet();
        Car clio = new Car("12345-A-6", "Renault", 42000);

        System.out.println(fleet.addCar(clio));
        System.out.println(fleet.addCar(new Car(" 12345-a-6 ", "Dacia", 10)));
        System.out.println(fleet.addCar(new Car("777-B-1", "Dacia", 15000)));
        System.out.println(fleet.addCar(new Car("90-C-26", "Renault", 15000)));

        clio.drive(500);

        System.out.println(fleet.contains(new Car("12345-A-6", "Renault", 0)));
        System.out.println(fleet.size());
        System.out.println(fleet.countByBrand());
        System.out.println(fleet.sortedByMileage());
        System.out.println(new Car("1-A-1", "Fiat", 0).equals(null));
        try {
            new Car("   ", "Fiat", 0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}