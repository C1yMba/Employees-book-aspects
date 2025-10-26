package md.hibernate.repository;

import lombok.extern.slf4j.Slf4j;
import md.entity.EmployeesDepartments;
import md.hibernate.RepositoryDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Slf4j
public class DepartmentDao implements RepositoryDao<EmployeesDepartments, Long> {
    private final SessionFactory sessionFactory;
    @Autowired
    public DepartmentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(EmployeesDepartments entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public EmployeesDepartments findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(EmployeesDepartments.class, id);
        }
    }

    @Override
    public List<EmployeesDepartments> findAll() {
        String hql = "FROM EmployeesDepartments";
        Query<EmployeesDepartments> query = sessionFactory.openSession().createQuery(hql, EmployeesDepartments.class);
        return query.list();
    }

    @Override
    public void update(EmployeesDepartments entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(EmployeesDepartments entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }
}
