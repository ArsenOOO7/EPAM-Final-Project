package com.arsen.epam.internet.shop.repository.ban;

import com.arsen.epam.internet.shop.database.DBManager;
import com.arsen.epam.internet.shop.database.Query;
import com.arsen.epam.internet.shop.entity.user.ban.UserBan;
import com.arsen.epam.internet.shop.repository.Specification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ban repository
 *
 * @author Arsen Sydoryk
 *
 */
public class BanRepository implements IBanRepository {
    @Override
    public void save(UserBan entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getInstance().getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(Query.SAVE_BAN, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setString(2, entity.getReason());
            preparedStatement.setInt(3, entity.getEndTime());

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
    public void update(UserBan entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getInstance().getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(Query.UPDATE_BAN);

            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setString(2, entity.getReason());
            preparedStatement.setInt(3, entity.getEndTime());

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
    public void delete(UserBan entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(Query.DELETE_BAN);

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
        UserBan ban = new UserBan();
        ban.setId(id);
        delete(ban);
    }

    @Override
    public UserBan findOne(Specification specification) {
        return findAll(specification).stream().findFirst().orElse(null);
    }

    @Override
    public List<UserBan> findAll(Specification specification) {
        List<UserBan> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getInstance().getConnection();
            preparedStatement = specification.getStatement(connection);

            connection.setAutoCommit(false);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    UserBan ban = new UserBan();
                    ban.extract(resultSet);

                    list.add(ban);
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
