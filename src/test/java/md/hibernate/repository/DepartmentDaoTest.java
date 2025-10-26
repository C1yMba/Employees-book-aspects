package md.hibernate.repository;

import md.config.AppConfig;
import md.entity.EmployeesDepartments;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentDaoTest {
    DepartmentDao manageDepartment;

    @BeforeEach
    void setUp(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        manageDepartment = applicationContext.getBean(DepartmentDao.class);
    }

    @Test
    void save() {
        EmployeesDepartments department = new EmployeesDepartments();
        department.setName("Test Department");
        manageDepartment.save(department);
        assertNotNull(department.getId());
        EmployeesDepartments retrievedDepartment = manageDepartment.findById(department.getId());
        assertEquals("Test Department", retrievedDepartment.getName());
    }
    @Test
    void findById() {
        EmployeesDepartments department = new EmployeesDepartments();
        department.setName("Test Department");
        manageDepartment.save(department);
        EmployeesDepartments retrievedDepartment = manageDepartment.findById(department.getId());
        assertNotNull(retrievedDepartment);
        assertEquals("Test Department", retrievedDepartment.getName());
    }

    @Test
    void findAll() {
        EmployeesDepartments department1 = new EmployeesDepartments();
        department1.setName("Department 1");
        manageDepartment.save(department1);
        EmployeesDepartments department2 = new EmployeesDepartments();
        department2.setName("Department 2");
        manageDepartment.save(department2);
        List<EmployeesDepartments> allDepartments = manageDepartment.findAll();
        assertNotNull(allDepartments);
        assertEquals(2, allDepartments.size());
    }

    @Test
    void update() {
        EmployeesDepartments department = new EmployeesDepartments();
        department.setName("Test Department");
        manageDepartment.save(department);
        department.setName("Updated Department");
        manageDepartment.update(department);
        EmployeesDepartments updatedDepartment = manageDepartment.findById(department.getId());
        assertNotNull(updatedDepartment);
        assertEquals("Updated Department", updatedDepartment.getName());
    }

    @Test
    void delete() {
        EmployeesDepartments department = new EmployeesDepartments();
        department.setName("Test Department");
        manageDepartment.save(department);
        Long departmentId = department.getId();
        manageDepartment.delete(department);
        EmployeesDepartments deletedDepartment = manageDepartment.findById(departmentId);
        assertNull(deletedDepartment);
    }
}