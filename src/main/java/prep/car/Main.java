package prep.car;


import java.util.*;

/**
 *
 * @author Yousef ELHAID
 */
final class Car {
    private final String registrationNumber;
    private final String brand;
    private int mileage;

    Car(String registrationNumber, String brand, int mileage) {
        if (registrationNumber.isEmpty()) {
            throw new NoSuchElementException();
        }
        this.registrationNumber = registrationNumber.trim().toUpperCase();
        this.brand = brand;
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
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Car)) return false;
        return this.registrationNumber.equals(((Car) other).registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }

    @Override
    public String toString() {
        return registrationNumber + " (" + brand + ", " + mileage + " km)";
    }
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
        Map<String, Integer> countByBrand = new HashMap<>();
        for (Car car : cars) {
            countByBrand.merge(car.getBrand(),1,Integer::sum);
        }
        return Collections.unmodifiableMap(countByBrand);
    }

    List<Car> sortedByMileage() {
        List<Car> carList = new ArrayList<>(cars);
        carList.sort(Comparator.comparingInt(Car::getMileage).thenComparing(Car::getRegistrationNumber));
        return carList;
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
    }
}