package md.hibernate.repository;

import lombok.extern.slf4j.Slf4j;
import md.entity.Project;
import md.hibernate.RepositoryDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ProjectDao implements RepositoryDao<Project, Long> {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProjectDao( SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Project entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
    }
    @Override
    public Project findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Project.class, id);
        }
    }

    @Override
    public List<Project> findAll() {
        String hql = "FROM Project"; // Corrected HQL query
        Query<Project> query = sessionFactory.openSession().createQuery(hql, Project.class);
        return query.list();
    }
    @Override
    public void update(Project entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    public void delete(Project entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }
}

