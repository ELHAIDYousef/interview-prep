package prep2.rh2;

import java.util.*;

final class Employee {
    private final String name;
    private final Date hireDate;
    private final List<String> skills;

    Employee(String name, Date hireDate, List<String> skills) {
        Objects.requireNonNull(name, "name");
        Objects.requireNonNull(hireDate, "hireDate");
        Objects.requireNonNull(skills, "skills");
        String normalizedName = name.trim();
        if (normalizedName.isEmpty()) {
            throw new IllegalArgumentException("Name must not be blank");
        }
        for (String skill : skills) {
            if (skill == null) {
                throw new NullPointerException("Null skills are not allowed");
            }
        }

        this.name = normalizedName;
        this.hireDate = new Date(hireDate.getTime());
        this.skills = List.copyOf(new LinkedHashSet<>(skills));
    }

    String getName() {
        return name;
    }

    Date getHireDate() {
        return new Date(hireDate.getTime());
    }

    List<String> getSkills() {
        return List.copyOf(skills);
    }

    public Employee withSkill(String skill) {
        Objects.requireNonNull(skill, "skill");
        if (skills.contains(skill)) {
            return this;
        }
        List<String> newSkills = new ArrayList<>(skills);
        newSkills.add(skill);
        return new Employee(name, hireDate, newSkills);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Employee other)) {
            return false;
        }
        return  this.name.equals(other.name) && this.hireDate.equals(other.hireDate) && this.skills.equals(other.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hireDate, skills);
    }
}

public class Main {

    public static void main(String[] args) {
        List<String> skills = new ArrayList<>(List.of("Java", "SQL", "Java"));
        Date hireDate = new Date(0);
        Employee ali = new Employee(" Ali ", hireDate, skills);

        skills.add("Python");
        hireDate.setTime(86_400_000L);
        ali.getHireDate().setTime(172_800_000L);
        try {
            ali.getSkills().add("Go");
        } catch (UnsupportedOperationException e) {
            System.out.println("Ajout refusé");
        }
        Employee withSpring = ali.withSkill("Spring");
        Employee sameAli = new Employee("Ali", new Date(0), List.of("Java", "SQL"));

        System.out.println(ali.getSkills());
        System.out.println(withSpring.getSkills());
        System.out.println(ali.getHireDate().getTime());
        System.out.println(ali.withSkill("Java") == ali);
        System.out.println(ali.equals(sameAli));
        System.out.println(new HashSet<>(List.of(ali, sameAli, withSpring)).size());
        try {
            new Employee("Omar", new Date(0), Arrays.asList("Java", null));
        } catch (NullPointerException e) {
            System.out.println("Compétence null refusée");
        }
        try {
            new Employee("   ", new Date(0), List.of());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
