package com.arsen.epam.internet.shop.repository;

import com.arsen.epam.internet.shop.entity.Entity;

import java.util.List;


/**
 * Root of all repositories
 *
 * @param <T> entity
 *
 * @author Arse Sydoryk
 */
public interface Repository<T extends Entity> {

    /**
     * Method saves entity to DB
     *
     * @param entity entity
     */
    void save(T entity);

    /**
     * Method updates entity in DB
     *
     * @param entity entity
     */
    void update(T entity);

    /**
     * Method deletes entity from DB
     *
     * @param entity entity
     */
    void delete(T entity);

    /**
     * Method deletes entity from DB by it's id
     *
     * @param id of entity
     */
    void delete(int id);

    /**
     * Method returns one entity or null
     *
     * @param specification of search
     * @return entity
     */
    T findOne(Specification specification);

    /**
     * Method returns list of entities from DB by search criteria
     *
     * @param specification of search
     * @return list of entities
     */
    List<T> findAll(Specification specification);

}
