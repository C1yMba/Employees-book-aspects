package md.hibernate.repository;

import md.config.AppConfig;
import md.entity.EmployeesDepartments;
import md.entity.EmployeesInfo;
import md.entity.EmployeesPositions;
import md.entity.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonDaoTest {
    PersonDao manageEmployeesInfo;
    DepartmentDao manageDepartment;
    EmployeesPositionsDao managePosition;
    ProjectDao manageProject;
    EmployeesDepartments department;
    EmployeesPositions position;
    Project project;


    @BeforeEach
    void setUp() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        manageEmployeesInfo = applicationContext.getBean(PersonDao.class);
        manageDepartment = applicationContext.getBean(DepartmentDao.class);
        managePosition = applicationContext.getBean(EmployeesPositionsDao.class);
        manageProject = applicationContext.getBean(ProjectDao.class);

        department = new EmployeesDepartments();
        department.setName("Test Department");
        manageDepartment.save(department);

        position = new EmployeesPositions();
        position.setName("Test Position");
        managePosition.save(position);

        project = new Project();
        project.setName("Test Project");
        manageProject.save(project);
    }

    @Test
    void save() {
        EmployeesInfo employeeInfo =
                new EmployeesInfo("John Doe", 30, 5000.0, department,position,List.of(project));
        manageEmployeesInfo.save(employeeInfo);
        assertNotNull(employeeInfo.getId());
        EmployeesInfo retrievedInfo = manageEmployeesInfo.findById(employeeInfo.getId());
        assertNotNull(retrievedInfo);
        assertEquals("John Doe", retrievedInfo.getName());
        assertEquals(30, retrievedInfo.getAge());
        assertEquals(5000.0, retrievedInfo.getSalary());
        assertEquals("Test Department", retrievedInfo.getDepartment().getName());
        assertEquals("Test Position", retrievedInfo.getPosition().getName());
        assertEquals(1, retrievedInfo.getProjects().size());
        assertEquals("Test Project", retrievedInfo.getProjects().get(0).getName());
    }


    @Test
    void findById() {
        EmployeesInfo employeeInfo = new EmployeesInfo("John Doe", 30, 5000, department, position, List.of(project));
        manageEmployeesInfo.save(employeeInfo);
        Long employeeId = employeeInfo.getId();
        EmployeesInfo retrievedInfo = manageEmployeesInfo.findById(employeeId);
        assertNotNull(retrievedInfo);
        assertEquals("John Doe", retrievedInfo.getName());
        assertEquals(30, retrievedInfo.getAge());
        assertEquals(5000.0, retrievedInfo.getSalary());
        assertEquals("Test Department", retrievedInfo.getDepartment().getName());
        assertEquals("Test Position", retrievedInfo.getPosition().getName());
        assertEquals("Test Project", retrievedInfo.getProjects().get(0).getName());
    }

    @Test
    void findAll() {
        EmployeesInfo employee1= new EmployeesInfo("John Doe", 30, 5000.0, department, position, List.of(project));
        EmployeesInfo employee2 = new EmployeesInfo("Jane Doe", 25, 6000.0, department, position, List.of(project));
        manageEmployeesInfo.save(employee1);
        manageEmployeesInfo.save(employee2);
        List<EmployeesInfo> allEmployees = manageEmployeesInfo.findAll();
        EmployeesInfo retrievedInfo = manageEmployeesInfo.findById(employee1.getId());
        assertEquals("John Doe", retrievedInfo.getName());
        assertEquals(30, retrievedInfo.getAge());
        assertEquals(5000.0, retrievedInfo.getSalary());
        assertEquals("Test Department", retrievedInfo.getDepartment().getName());
        assertEquals("Test Position", retrievedInfo.getPosition().getName());
        assertEquals("Test Project", retrievedInfo.getProjects().get(0).getName());
        assertNotNull(allEmployees);
        assertEquals(2, allEmployees.size());
        EmployeesInfo retrievedInfoTwo = manageEmployeesInfo.findById(employee2.getId());
        assertEquals("Jane Doe", retrievedInfoTwo.getName());
        assertEquals(25, retrievedInfoTwo.getAge());
        assertEquals(6000.0, retrievedInfoTwo.getSalary());
        assertEquals("Test Department", retrievedInfoTwo.getDepartment().getName());
        assertEquals("Test Position", retrievedInfoTwo.getPosition().getName());
        assertEquals("Test Project", retrievedInfoTwo.getProjects().get(0).getName());

    }

    @Test
    void update() {
        EmployeesInfo employeeInfo = new EmployeesInfo("John Doe", 30, 5000.0, department, position, List.of(project));
        manageEmployeesInfo.save(employeeInfo);
        employeeInfo.setName("Updated John Doe");
        manageEmployeesInfo.update(employeeInfo);
        EmployeesInfo updatedInfo = manageEmployeesInfo.findById(employeeInfo.getId());
        assertNotNull(updatedInfo);
        assertEquals("Updated John Doe", updatedInfo.getName());
    }

    @Test
    void delete() {
        EmployeesInfo employeeInfo = new EmployeesInfo("John Doe", 30, 5000.0, department, position, List.of(project));
        manageEmployeesInfo.save(employeeInfo);
        Long employeeId = employeeInfo.getId();
        manageEmployeesInfo.delete(employeeInfo);
        EmployeesInfo deletedInfo = manageEmployeesInfo.findById(employeeId);
        assertNull(deletedInfo);
    }
}