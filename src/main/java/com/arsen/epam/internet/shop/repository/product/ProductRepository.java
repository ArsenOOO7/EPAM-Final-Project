package com.arsen.epam.internet.shop.repository.product;

import com.arsen.epam.internet.shop.database.DBConnection;
import com.arsen.epam.internet.shop.database.Query;
import com.arsen.epam.internet.shop.entity.product.Product;
import com.arsen.epam.internet.shop.repository.Specification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Product repository
 *
 * @author Arsen Sydoryk
 *
 */
public class ProductRepository implements IProductRepository{

    @Override
    public void save(Product entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(Query.SAVE_PRODUCT, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getShortName());
            preparedStatement.setString(2, entity.getFullName());
            preparedStatement.setString(3, entity.getDescription());
            preparedStatement.setInt(4, entity.getAmount());
            preparedStatement.setDouble(5, entity.getPrice());
            preparedStatement.setInt(6, entity.getColor().getId());
            preparedStatement.setInt(7, entity.getSizeUnit().getId());
            preparedStatement.setInt(8, entity.getSizeValue());
            preparedStatement.setInt(9, entity.getCategoryId());
            preparedStatement.setInt(10, entity.getPreviewImageId());

            preparedStatement.executeUpdate();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                if(resultSet.next()){
                    entity.setId(resultSet.getInt(1));
                }
            }

            connection.commit();

        }catch (SQLException exception){
            DBConnection.rollback(connection);
            exception.printStackTrace();
        }finally {
            DBConnection.close(preparedStatement, connection);
        }
    }

    @Override
    public void update(Product entity) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(Query.UPDATE_PRODUCT);

            preparedStatement.setString(1, entity.getShortName());
            preparedStatement.setString(2, entity.getFullName());
            preparedStatement.setString(3, entity.getDescription());
            preparedStatement.setInt(4, entity.getAmount());
            preparedStatement.setDouble(5, entity.getPrice());
            preparedStatement.setInt(6, entity.getColor().getId());
            preparedStatement.setInt(7, entity.getSizeUnit().getId());
            preparedStatement.setInt(8, entity.getSizeValue());
            preparedStatement.setInt(9, entity.getCategoryId());
            preparedStatement.setInt(10, entity.getPreviewImageId());
            preparedStatement.setInt(11, entity.getStartDate());
            preparedStatement.setInt(12, entity.getId());

            preparedStatement.executeUpdate();
            connection.commit();

        }catch (SQLException exception){
            DBConnection.rollback(connection);
            exception.printStackTrace();
        }finally {
            DBConnection.close(preparedStatement, connection);
        }

    }

    @Override
    public void delete(Product entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(Query.DELETE_PRODUCT);

            connection.setAutoCommit(false);
            preparedStatement.setInt(1, entity.getId());

            preparedStatement.executeUpdate();

            if(entity.getPreviewImageId() > 0){
                deleteImage(connection, entity.getPreviewImageId());
            }

            connection.commit();
        }catch (SQLException exception){
            DBConnection.rollback(connection);
            exception.printStackTrace();
        }finally {
            DBConnection.close(preparedStatement, connection);
        }
    }

    @Override
    public void delete(int id){
        Product product = new Product();
        product.setId(id);
        delete(product);
    }

    @Override
    public Product findOne(Specification specification) {
        return findAll(specification).stream().findFirst().orElse(null);
    }

    @Override
    public List<Product> findAll(Specification specification) {
        List<Product> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBConnection.getInstance().getConnection();
            preparedStatement = specification.getStatement(connection);

            connection.setAutoCommit(false);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    Product product = new Product();
                    product.extract(resultSet);

                    list.add(product);
                }
            }
            connection.commit();
        }catch (SQLException exception){
            DBConnection.rollback(connection);
            exception.printStackTrace();
        }finally {
            DBConnection.close(preparedStatement, connection);
        }

        return list;
    }

    private void deleteImage(Connection connection, int id){

        try{

            PreparedStatement preparedStatement = connection.prepareStatement(Query.DELETE_IMAGE);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        }catch (SQLException exception){
            exception.printStackTrace();;
        }

    }
}
