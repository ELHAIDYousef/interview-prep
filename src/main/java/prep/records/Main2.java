package prep.records;

import java.util.*;
/**
 *
 * @author Yousef ELHAID
 */
final class Employee {
    private final String name;
    private final Date hireDate;
    private final List<String> skills;

    Employee(String name, Date hireDate, List<String> skills) {
        this.name = name;
        this.hireDate = new Date(hireDate.getTime());
        this.skills = List.copyOf(skills);
    }

    String getName() {
        return name;
    }

    Date getHireDate() {
        return new Date(hireDate.getTime());
    }

    List<String> getSkills() {
        return skills;
    }

    Employee withSkill(String skill) {
        List<String> newSkills = new ArrayList<>(skills);
        newSkills.add(skill);
        return new Employee(name, hireDate, newSkills);
    }
}

public class Main2 {
    public static void main(String[] args) {
        List<String> skills = new ArrayList<>(List.of("Java"));
        Date hireDate = new Date(0);
        Employee ali = new Employee("Ali", hireDate, skills);

        skills.add("Python");
        hireDate.setTime(86_400_000L);
        ali.getHireDate().setTime(172_800_000L);
        try {
            ali.getSkills().add("Go");
        } catch (UnsupportedOperationException e) {
            System.out.println("Ajout refusé");
        }
        Employee withSpring = ali.withSkill("Spring");

        System.out.println(ali.getSkills());
        System.out.println(withSpring.getSkills());
        System.out.println(ali.getHireDate().getTime());
        System.out.println(ali == withSpring);
    }
}