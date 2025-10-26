package md.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employees_positions")
public class EmployeesPositions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "position")
    private List<EmployeesInfo> employee;

    public EmployeesPositions(Long id, String name, List<EmployeesInfo> employee) {
        this.id = id;
        this.name = name;
        this.employee = employee;
    }
}
