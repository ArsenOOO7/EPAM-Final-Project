package com.arsen.epam.internet.shop.repository.category;

import com.arsen.epam.internet.shop.database.DBManager;
import com.arsen.epam.internet.shop.database.Query;
import com.arsen.epam.internet.shop.entity.Category;
import com.arsen.epam.internet.shop.repository.Specification;
import com.arsen.epam.internet.shop.repository.category.specification.CategoryIdSpecification;
import com.arsen.epam.internet.shop.service.cache.Cache;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Category repository
 *
 * @author Arsen Sydoryk
 *
 */
public class CategoryRepository implements ICategoryRepository{
    @Override
    public void save(Category entity) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getInstance().getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(Query.SAVE_CATEGORY, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getIdentifier());
            preparedStatement.setString(2, entity.getLocaleUA());
            preparedStatement.setString(3, entity.getLocaleEN());

            preparedStatement.executeUpdate();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                if(resultSet.next()){
                    entity.setId(resultSet.getInt(1));
                }
            }

            connection.commit();

        }catch (SQLException exception){
            DBManager.rollback(connection);
            exception.printStackTrace();
        }finally {
            DBManager.close(preparedStatement, connection);
        }

        addCache(entity);

    }

    @Override
    public void update(Category entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getInstance().getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(Query.UPDATE_CATEGORY);

            preparedStatement.setString(1, entity.getIdentifier());
            preparedStatement.setString(2, entity.getLocaleUA());
            preparedStatement.setString(3, entity.getLocaleEN());
            preparedStatement.setInt(4, entity.getId());

            preparedStatement.executeUpdate();
            connection.commit();

        }catch (SQLException exception){
            DBManager.rollback(connection);
            exception.printStackTrace();
        }finally {
            DBManager.close(preparedStatement, connection);
        }

        updateCache(entity);
    }

    @Override
    public void delete(Category entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(Query.DELETE_CATEGORY);

            connection.setAutoCommit(false);
            preparedStatement.setInt(1, entity.getId());

            preparedStatement.executeUpdate();

            connection.commit();
        }catch (SQLException exception){
            DBManager.rollback(connection);
            exception.printStackTrace();
        }finally {
            DBManager.close(preparedStatement, connection);
        }

        deleteCache(entity);

    }

    @Override
    public void delete(int id){
        Category category = new Category();
        category.setId(id);
        delete(category);
    }


    @Override
    public Category findOne(int id){
        Cache cache = Cache.getInstance();
        if(cache.hasAttribute("categories")){
            return findAll(null).stream().filter(category -> category.getId() == id)
                    .findFirst()
                    .orElse(null);
        }

        return findOne(new CategoryIdSpecification(id));
    }

    @Override
    public Category findOne(Specification specification) {
        return findAll(specification).stream().findFirst().orElse(null);
    }

    @Override
    public List<Category> findAll(Specification specification) {

        Cache cache = Cache.getInstance();
        if(cache.hasAttribute("categories")){
            return (List<Category>) cache.getAttribute("categories");
        }

        List<Category> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getInstance().getConnection();
            preparedStatement = specification.getStatement(connection);

            connection.setAutoCommit(false);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    Category category = new Category();
                    category.extract(resultSet);

                    list.add(category);
                }
            }
            connection.commit();
        }catch (SQLException exception){
            DBManager.rollback(connection);
            exception.printStackTrace();
        }finally {
            DBManager.close(preparedStatement, connection);
        }

        cache.setAttribute("categories", list);
        return list;
    }


    /**
     * Deletes category from cache
     *
     * @param entity category
     */
    private void deleteCache(Category entity){
        Cache cache = Cache.getInstance();
        if(cache.hasAttribute("categories")){
            List<Category> list = findAll(null);
            Iterator<Category> categoryIterator = list.iterator();
            while(categoryIterator.hasNext()){
                Category category = categoryIterator.next();
                if(category.getId() == entity.getId()){
                    categoryIterator.remove();
                    break;
                }
            }
        }
    }


    /**
     * Updates category in cache
     *
     * @param category entity
     */
    private void updateCache(Category category){
        Cache cache = Cache.getInstance();
        if(cache.hasAttribute("categories")){
            List<Category> categories = findAll(null);

            ListIterator<Category> categoryIterator = categories.listIterator();
            while(categoryIterator.hasNext()){
                if(categoryIterator.next().getId() == category.getId()){
                    categoryIterator.set(category);
                    break;
                }
            }
        }
    }


    /**
     * Adding category to the list in cache
     *
     * @param category entity
     */
    private void addCache(Category category){
        Cache cache = Cache.getInstance();
        if(cache.hasAttribute("categories")){
            findAll(null).add(category);
        }
    }

}
