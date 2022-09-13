package com.arsen.epam.internet.shop.repository.cart;

import com.arsen.epam.internet.shop.database.DBManager;
import com.arsen.epam.internet.shop.database.Query;
import com.arsen.epam.internet.shop.entity.cart.Cart;
import com.arsen.epam.internet.shop.entity.product.Product;
import com.arsen.epam.internet.shop.repository.Specification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Cart repository
 *
 * @author Arsen Sydoryk
 *
 */
public class CartRepository implements ICartRepository{

    @Override
    public void save(Cart entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getInstance().getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(Query.SAVE_CART, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getProductId());
            preparedStatement.setInt(3, entity.getAmount());
            preparedStatement.setInt(4, entity.getPrice());
            preparedStatement.setInt(5, entity.getStatus().getId());

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
    public void update(Cart entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getInstance().getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(Query.UPDATE_CART);

            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getProductId());
            preparedStatement.setInt(3, entity.getAmount());
            preparedStatement.setInt(4, entity.getPrice());
            preparedStatement.setTimestamp(5, entity.getDate());
            preparedStatement.setInt(6, entity.getStatus().getId());
            preparedStatement.setInt(7, entity.getId());

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
    public void delete(Cart entity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(Query.DELETE_CART);

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
        Cart cart = new Cart();
        cart.setId(id);
        delete(cart);
    }

    @Override
    public Cart findOne(Specification specification) {
        return findAll(specification).stream().findFirst().orElse(null);
    }

    @Override
    public List<Cart> findAll(Specification specification) {
        List<Cart> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBManager.getInstance().getConnection();
            preparedStatement = specification.getStatement(connection);

            connection.setAutoCommit(false);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    Cart cart = new Cart();
                    cart.extract(resultSet);

                    cart.setProduct(findProduct(connection, cart.getProductId()));
                    list.add(cart);
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


    private Product findProduct(Connection connection, int id){

        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products_view WHERE id = ?");
            preparedStatement.setInt(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    Product product = new Product();
                    product.extract(resultSet);

                    return product;
                }
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }

        return null;
    }
}
