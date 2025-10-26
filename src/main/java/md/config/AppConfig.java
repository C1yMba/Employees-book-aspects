package md.config;

import md.aspects.ValidationAspect;
import md.hibernate.repository.DepartmentDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"md"})
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public ValidationAspect validationAspect() {
        return new ValidationAspect();
    }
    @Bean
    public DataSource dataSource(
            @Value("${jdbc.driverClassName}") String driverClassName,
            @Value("${jdbc.url}") String url,
            @Value("${jdbc.username}") String username,
            @Value("${jdbc.password}") String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean hibernateSessionsFactory(
            DataSource dataSource,
            @Value("${hibernate.dialect}") String dialect,
            @Value("${hibernate.show_sql}") String showSql,
            @Value("${hibernate.hbm2ddl.auto}") String hbm2ddlAuto,
            @Value("${hibernate.c3p0.min_size}") int c3p0MinSize,
            @Value("${hibernate.c3p0.max_size}") int c3p0MaxSize,
            @Value("${hibernate.c3p0.timeout}") int c3p0Timeout,
            @Value("${hibernate.c3p0.max_statements}") int c3p0MaxStatements,
            @Value("${hibernate.c3p0.idle_test_period}") int c3p0IdleTestPeriod) {

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("entity");
        sessionFactory.setHibernateProperties(hibernateProperties(dialect, showSql, hbm2ddlAuto,
                c3p0MinSize, c3p0MaxSize, c3p0Timeout, c3p0MaxStatements, c3p0IdleTestPeriod));
        sessionFactory.setAnnotatedClasses(
                md.entity.EmployeesInfo.class,
                md.entity.EmployeesDepartments.class,
                md.entity.EmployeesPositions.class,
                md.entity.Project.class
        );
        return sessionFactory;
    }

    public Properties hibernateProperties(String dialect, String showSql, String hbm2ddlAuto,
                                          int c3p0MinSize, int c3p0MaxSize, int c3p0Timeout,
                                          int c3p0MaxStatements, int c3p0IdleTestPeriod) {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.show_sql", showSql);
        properties.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        properties.setProperty("hibernate.c3p0.min_size", String.valueOf(c3p0MinSize));
        properties.setProperty("hibernate.c3p0.max_size", String.valueOf(c3p0MaxSize));
        properties.setProperty("hibernate.c3p0.timeout", String.valueOf(c3p0Timeout));
        properties.setProperty("hibernate.c3p0.max_statements", String.valueOf(c3p0MaxStatements));
        properties.setProperty("hibernate.c3p0.idle_test_period", String.valueOf(c3p0IdleTestPeriod));
        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager(
            @Qualifier("hibernateSessionsFactory") LocalSessionFactoryBean hibernateSessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(hibernateSessionFactory.getObject());
        return transactionManager;
    }


//    @Bean
//    @Qualifier("hibernateSessionsFactory")
//    public PersonDao personDao(LocalSessionFactoryBean hibernateSessionFactory) {
//        return new PersonDao(hibernateSessionFactory.getObject());
//    }


    @Bean
    public DepartmentDao departmentDao(
            @Qualifier("hibernateSessionsFactory") LocalSessionFactoryBean hibernateSessionFactory) {
        return new DepartmentDao(hibernateSessionFactory.getObject());
    }

//    @Bean
//    public EmployeesPositionsDao employeesPossitionsDao(
//            @Qualifier("hibernateSessionsFactory") LocalSessionFactoryBean hibernateSessionFactory) {
//        return new EmployeesPositionsDao(hibernateSessionFactory.getObject());
//    }

//    @Bean
//    public ProjectDao projectDao(
//            @Qualifier("hibernateSessionsFactory") LocalSessionFactoryBean hibernateSessionFactory) {
//        return new ProjectDao(hibernateSessionFactory.getObject());
//    }
}
