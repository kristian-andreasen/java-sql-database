package demo;

import java.util.List;

/**
 * A generic repository interface for performing CRUD (create, read, update, delete) operations on entities.
 *
 * @param <T> the type of the entity to be managed
 * @param <U> the type of the entity's ID
 */
public interface CRUDRepository<T, U> {

    /**
     * Returns a list of all entities of type T in the repository.
     *
     * @return a list of entities
     */
    List<T> findAll();

    T findById(U id);

    int insert(T object);

    int update(T object);

    int delete(T object);

    int deleteById(U id);
}