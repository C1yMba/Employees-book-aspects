package md.util;

import md.aspects.ValidationAspect;
import md.entity.EmployeesInfo;
import md.exceptions.InvalidAgeException;
import md.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ValidationAspectTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ValidationAspect validationAspect;

    @Test
    void saveEmployeeTestNegative() {
        EmployeesInfo employee = new EmployeesInfo();
        employee.setId(1L);
        employee.setAge(-3);
        assertThrows(InvalidAgeException.class, () -> employeeService.saveEmployee(employee));
    }
}
