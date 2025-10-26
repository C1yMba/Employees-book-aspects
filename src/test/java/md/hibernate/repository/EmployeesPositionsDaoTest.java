package md.hibernate.repository;

import md.config.AppConfig;
import md.entity.EmployeesPositions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeesPositionsDaoTest {
    EmployeesPositionsDao managePosition;

    @BeforeEach
    void setUp() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        managePosition = applicationContext.getBean(EmployeesPositionsDao.class);
    }

    @Test
    void save() {
        EmployeesPositions position = new EmployeesPositions();
        position.setName("Test Position");
        managePosition.save(position);
        assertNotNull(position.getId());
        EmployeesPositions retrievedPosition = managePosition.findById(position.getId());
        assertEquals("Test Position", retrievedPosition.getName());
    }

    @Test
    void findById() {
        EmployeesPositions position = new EmployeesPositions();
        position.setName("Test Position");
        managePosition.save(position);
        EmployeesPositions retrievedPosition = managePosition.findById(position.getId());
        assertNotNull(retrievedPosition);
        assertEquals("Test Position", retrievedPosition.getName());
    }

    @Test
    void findAll() {
        EmployeesPositions position1 = new EmployeesPositions();
        position1.setName("Position 1");
        managePosition.save(position1);
        EmployeesPositions position2 = new EmployeesPositions();
        position2.setName("Position 2");
        managePosition.save(position2);
        List<EmployeesPositions> allPositions = managePosition.findAll();
        System.out.println(allPositions);
        assertNotNull(allPositions);
        assertEquals(2, allPositions.size());
    }

    @Test
    void update() {
        EmployeesPositions position = new EmployeesPositions();
        position.setName("Test Position");
        managePosition.save(position);
        position.setName("Updated Position");
        managePosition.update(position);
        EmployeesPositions updatedPosition = managePosition.findById(position.getId());
        assertNotNull(updatedPosition);
        assertEquals("Updated Position", updatedPosition.getName());
    }

    @Test
    void delete() {
        EmployeesPositions position = new EmployeesPositions();
        position.setName("Test Position");
        managePosition.save(position);
        Long positionId = position.getId();
        managePosition.delete(position);
        EmployeesPositions deletedPosition = managePosition.findById(positionId);
        assertNull(deletedPosition);
    }
}
