package md;

import md.entity.EmployeesDepartments;
import md.entity.EmployeesInfo;
import md.entity.EmployeesPositions;
import md.entity.Project;
import md.hibernate.repository.DepartmentDao;
import md.hibernate.repository.EmployeesPositionsDao;
import md.hibernate.repository.PersonDao;
import md.hibernate.repository.ProjectDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestSpring {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        PersonDao managePerson =  applicationContext.getBean(PersonDao.class);
        DepartmentDao manageDepartment = applicationContext.getBean(DepartmentDao.class);
        EmployeesPositionsDao manageEmployeesPositions = applicationContext.getBean(EmployeesPositionsDao.class);
        ProjectDao manageProject = applicationContext.getBean(ProjectDao.class);
//        EmployeeService employeeService = applicationContext.getBean(EmployeeService.class);;
//        EmployeesInfo employeeGeorge = new EmployeesInfo();
//        employeeGeorge.setAge(-5);
//        employeeService.saveEmployee(employeeGeorge);



        EmployeesDepartments marketingDepartment = new EmployeesDepartments();
        marketingDepartment.setName("Marketing");
        EmployeesDepartments bankingDepartment = new EmployeesDepartments();
        bankingDepartment.setName("Banking");
        //Crud for Department
        manageDepartment.save(bankingDepartment);
        manageDepartment.save(marketingDepartment);
//        System.out.println(manageDepartment.findAll());
//        System.out.println("------------------------------------");
//        manageDepartment.delete(marketingDepartment);
//        System.out.println(manageDepartment.findAll());
//        System.out.println("------------------------------------");
//        manageDepartment.save(marketingDepartment);

        EmployeesPositions developerPosition = new EmployeesPositions();
        developerPosition.setName("Developer");
        EmployeesPositions managerPosition = new EmployeesPositions();
        managerPosition.setName("Manager");
        //Crud for Positions
        manageEmployeesPositions.save(developerPosition);
        manageEmployeesPositions.save(managerPosition);
//        System.out.println(manageEmployeesPositions.findAll());
//        System.out.println("------------------------------------");
//        manageEmployeesPositions.delete(managerPosition);
//        System.out.println(manageEmployeesPositions.findAll());
//        System.out.println("------------------------------------");
//        manageEmployeesPositions.save(managerPosition);

        Project projectBanking = new Project();
        projectBanking.setName("Banking");
        Project projectEcommerce = new Project();
        projectEcommerce.setName("E-Commerce");

        //Crud for Projects
        manageProject.save(projectBanking);
        manageProject.save(projectEcommerce);
//        System.out.println(manageProject.findAll());
//        System.out.println("------------------------------------");
//        manageProject.delete(projectEcommerce);
//        System.out.println(manageProject.findAll());
//        System.out.println("------------------------------------");
//        manageProject.save(projectEcommerce);

        EmployeesInfo employeeAnn = new EmployeesInfo();
        employeeAnn.setPosition(manageEmployeesPositions.findById(2L));
        employeeAnn.setProjects(List.of(manageProject.findById(2L)));
        employeeAnn.setDepartment(manageDepartment.findById(2L));
//        employeeAnn.setProjects(manageProject.findAll());


//        employeeGeorge.setPosition(manageEmployeesPositions.findById(1L));
//        employeeGeorge.setProjects(List.of(manageProject.findById(1L)));
//        employeeGeorge.setDepartment(manageDepartment.findById(1L));



        //Crud for Employees
        managePerson.save(employeeAnn);
//        managePerson.save(employeeGeorge);
        System.out.println(managePerson.findAll());
        System.out.println("------------------------------------");


        //Delete Employee
//        managePerson.delete(employeeGeorge);
        System.out.println(managePerson.findAll());

 }
}
