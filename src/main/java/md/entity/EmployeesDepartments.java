package md.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Setter
@Table(name = "employees_departments")
public class EmployeesDepartments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department")
    private List<EmployeesInfo> employees;


    public EmployeesDepartments(Long id, String name, List<EmployeesInfo> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }
}