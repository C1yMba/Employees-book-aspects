package md.aspects;

import md.entity.EmployeesInfo;
import md.exceptions.InvalidAgeException;
import md.exceptions.InvalidSalaryException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class ValidationAspect {
    @Before("execution(* md.service.EmployeeService.saveEmployee(..)) && args(entity)")
    public void beforeSave(@NotNull EmployeesInfo entity) {
        System.out.println("Before save: " + entity);
            validateAge(entity.getAge());
            validateSalary(entity.getSalary());
        }

    private void validateAge(int age) {
        if (age < 0) {
            throw new InvalidAgeException("Возраст не может быть отрицательным");
        }
        if (age > 70) {
            throw new InvalidAgeException("Возраст слишком большой!");
        }
    }

    private void validateSalary(double salary) {
        if (salary < 0) {
            throw new InvalidSalaryException("Зарплата не может быть отрицательной");
        } else if ( salary > 8000){
            throw new InvalidSalaryException("Зарплата слишком большая");
        }
        }
}
