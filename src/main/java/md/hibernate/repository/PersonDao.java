package md.hibernate.repository;

import lombok.extern.slf4j.Slf4j;
import md.entity.EmployeesInfo;
import md.hibernate.RepositoryDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Slf4j
public class PersonDao implements RepositoryDao<EmployeesInfo, Long> {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(EmployeesInfo entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
    }
    @Override
    public EmployeesInfo findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(EmployeesInfo.class, id);
        }
    }

    @Override
    public List<EmployeesInfo> findAll() {
        String hql = "FROM EmployeesInfo";
        Query<EmployeesInfo> query = sessionFactory.openSession().createQuery(hql, EmployeesInfo.class);
        return query.list();
    }
    @Override
    public void update(EmployeesInfo entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    public void delete(EmployeesInfo entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }
}
