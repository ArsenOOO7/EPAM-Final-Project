package com.arsen.epam.internet.shop.repository.image;

import com.arsen.epam.internet.shop.database.DBManager;
import com.arsen.epam.internet.shop.database.Query;
import com.arsen.epam.internet.shop.entity.image.Image;
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
public class ImageRepository implements IImageRepository{

    @Override
    public void save(Image entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getInstance().getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(Query.SAVE_IMAGE, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getUuid());
            preparedStatement.setString(2, entity.getContentType().getContentType());
            preparedStatement.setBlob(3, entity.getData());

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
    }

    @Override
    public void update(Image entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getInstance().getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(Query.UPDATE_IMAGE);

            preparedStatement.setString(1, entity.getUuid());
            preparedStatement.setString(2, entity.getContentType().getContentType());
            preparedStatement.setBlob(3, entity.getData());
            preparedStatement.setInt(4, entity.getId());

            preparedStatement.executeUpdate();
            connection.commit();

        }catch (SQLException exception){
            DBManager.rollback(connection);
            exception.printStackTrace();
        }finally {
            DBManager.close(preparedStatement, connection);
        }
    }

    @Override
    public void delete(Image entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(Query.DELETE_IMAGE);

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
    }

    @Override
    public void delete(int id){
        Image image = new Image();
        image.setId(id);
        delete(image);
    }

    @Override
    public Image findOne(Specification specification) {
        return findAll(specification).stream().findFirst().orElse(null);
    }

    @Override
    public List<Image> findAll(Specification specification) {
        List<Image> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getInstance().getConnection();
            preparedStatement = specification.getStatement(connection);

            connection.setAutoCommit(false);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    Image image = new Image();
                    image.extract(resultSet);

                    list.add(image);
                }
            }
            connection.commit();
        }catch (SQLException exception){
            DBManager.rollback(connection);
            exception.printStackTrace();
        }finally {
            DBManager.close(preparedStatement, connection);
        }

        return list;
    }
}
