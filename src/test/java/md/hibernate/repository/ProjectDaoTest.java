package md.hibernate.repository;

import md.config.AppConfig;
import md.entity.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDaoTest {
    ProjectDao manageProject;

    @BeforeEach
    void setUp() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        manageProject = applicationContext.getBean(ProjectDao.class);
    }

    @Test
    void save() {
        Project project = new Project();
        project.setName("Test Project");
        manageProject.save(project);
        assertNotNull(project.getId());
        Project retrievedProject = manageProject.findById(project.getId());
        assertEquals("Test Project", retrievedProject.getName());
    }

    @Test
    void findById() {
        Project project = new Project();
        project.setName("Test Project");
        manageProject.save(project);
        Project retrievedProject = manageProject.findById(project.getId());
        assertNotNull(retrievedProject);
        assertEquals("Test Project", retrievedProject.getName());
    }

    @Test
    void findAll() {
        Project project1 = new Project();
        project1.setName("Project 1");
        manageProject.save(project1);
        Project project2 = new Project();
        project2.setName("Project 2");
        manageProject.save(project2);
        List<Project> allProjects = manageProject.findAll();
        assertNotNull(allProjects);
        assertEquals(2, allProjects.size());
    }

    @Test
    void update() {
        Project project = new Project();
        project.setName("Test Project");
        manageProject.save(project);
        project.setName("Updated Project");
        manageProject.update(project);
        Project updatedProject = manageProject.findById(project.getId());
        assertNotNull(updatedProject);
        assertEquals("Updated Project", updatedProject.getName());
    }

    @Test
    void delete() {
        Project project = new Project();
        project.setName("Test Project");
        manageProject.save(project);
        Long projectId = project.getId();
        manageProject.delete(project);
        Project deletedProject = manageProject.findById(projectId);
        assertNull(deletedProject);
    }
}
