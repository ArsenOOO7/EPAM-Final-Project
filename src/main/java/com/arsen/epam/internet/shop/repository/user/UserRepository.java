package com.arsen.epam.internet.shop.repository.user;

import com.arsen.epam.internet.shop.database.DBConnection;
import com.arsen.epam.internet.shop.database.Query;
import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.repository.Specification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User repository
 *
 * @author Arsen Sydoryk
 *
 */
public class UserRepository implements IUserRepository{

    @Override
    public void save(User entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(Query.SAVE_USER, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getLogin());
            preparedStatement.setString(5, entity.getPassword());
            preparedStatement.setDate(6, entity.getBirthDate());
            preparedStatement.setDouble(7, entity.getBalance());
            preparedStatement.setInt(8, entity.getStatus().getStatus());
            preparedStatement.setInt(9, entity.getAvatarId());

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
    public void update(User entity) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(Query.UPDATE_USER);

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getSurname());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getLogin());
            preparedStatement.setString(5, entity.getPassword());
            preparedStatement.setDate(6, entity.getBirthDate());
            preparedStatement.setDouble(7, entity.getBalance());
            preparedStatement.setInt(8, entity.getStatus().getStatus());
            preparedStatement.setInt(9, entity.getAvatarId());
            preparedStatement.setInt(10, entity.getId());

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
    public void delete(User entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBConnection.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(Query.DELETE_USER);

            connection.setAutoCommit(false);
            preparedStatement.setInt(1, entity.getId());

            preparedStatement.executeUpdate();

            if(entity.getAvatarId() > 0){
                deleteImage(connection, entity.getAvatarId());
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
        User user = new User();
        user.setId(id);
        delete(user);
    }

    @Override
    public User findOne(Specification specification) {
        return findAll(specification).stream().findFirst().orElse(null);
    }

    @Override
    public List<User> findAll(Specification specification) {
        List<User> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBConnection.getInstance().getConnection();
            preparedStatement = specification.getStatement(connection);

            connection.setAutoCommit(false);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    User user = new User();
                    user.extract(resultSet);

                    list.add(user);
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
