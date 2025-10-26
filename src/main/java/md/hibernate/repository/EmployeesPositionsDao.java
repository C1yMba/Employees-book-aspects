package md.hibernate.repository;

import lombok.extern.slf4j.Slf4j;
import md.entity.EmployeesPositions;
import md.hibernate.RepositoryDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class EmployeesPositionsDao implements RepositoryDao<EmployeesPositions, Long> {

    private final SessionFactory sessionFactory;

    @Autowired
    public EmployeesPositionsDao( SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(EmployeesPositions entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
    }
    @Override
    public EmployeesPositions findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(EmployeesPositions.class, id);
        }
    }

    @Override
    public List<EmployeesPositions> findAll() {
        String hql = "FROM EmployeesPositions";
        Query<EmployeesPositions> query = sessionFactory.openSession().createQuery(hql, EmployeesPositions.class);
        return query.list();
    }
    @Override
    public void update(EmployeesPositions entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    public void delete(EmployeesPositions entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }
}
