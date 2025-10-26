package md.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees_info")
public class EmployeesInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private EmployeesDepartments department;

    @Column(name = "salary")
    private double salary;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private EmployeesPositions position;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;

    public EmployeesInfo(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    public EmployeesInfo(String name, int age, double salary, EmployeesDepartments department,
                         EmployeesPositions position, List<Project> projects
    ) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.department = department;
        this.position = position;
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "\nИнформация о человеке: \nИмя: " + this.getName() + "\nВозраст: " + this.getAge() +
                "\nЗарплата: " + this.getSalary() +
                "\nДолжность: " + (this.getPosition() != null ? this.getPosition().getName() : "Не назначен на должность") +
                "\nДепартамент: " + (this.getDepartment() != null ? this.getDepartment().getName() : "Не добавили в департамент") +
                "\nПроекты: " + (this.getProjects() != null ? this.getProjects().toString() : "Не назначили проект");
    }

}
