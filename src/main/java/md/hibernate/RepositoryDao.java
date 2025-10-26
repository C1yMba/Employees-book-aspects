package md.hibernate;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryDao<T, ID> {
    T findById(ID id);
    void save(T entity);
    void update(T entity);
    void delete(T entity);
    List<T> findAll();
}