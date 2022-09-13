package com.arsen.epam.internet.shop.database;

import com.arsen.epam.internet.shop.repository.Specification;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

/**
 * A class-connector to database.
 *
 * @author Arsen Sydoryk
 */
public class DBManager {

    //InternetShop - the name of default data source
    public static final String DATA_SOURCE_DEFAULT = "jdbc/InternetShop";

    /**
     * DataSource, which contains database data from Context
     */
    private DataSource dataSource;


    /**
     * Connection address (for in-mem db)
     */
    private String connectionAddress;

    /**
     * DBConnection instance
     */
    private static DBManager instance;


    /**
     *
     * @param driverName driver name
     * @param connectionAddress connection address
     * @return DBConnection instance
     */
    public synchronized static DBManager getInstance(String driverName, String connectionAddress){
        if(instance == null){
            instance = new DBManager(driverName, connectionAddress);
        }
        return instance;
    }

    /**
     *
     * @return DBConnection instance
     */
    public synchronized static DBManager getInstance(){
        if(instance == null){
            instance = new DBManager();
        }
        return instance;
    }


    /**
     * Default constructor
     */
    private DBManager(){
        getDataSource();
    }

    /**
     * Constructor with parameter address
     * @param driverName driver name
     * @param connectionAddress data source name
     */
    private DBManager(String driverName, String connectionAddress){
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.connectionAddress = connectionAddress;
    }

    /**
     * This method reads context file and initializes dataSource
     */
    private void getDataSource(){
        try {

            Context initContext = new InitialContext();
            Context envContext = (Context)initContext.lookup("java:/comp/env");

            dataSource = (DataSource)envContext.lookup(DATA_SOURCE_DEFAULT);

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns the Connection to DB from pool
     *
     * @return DB connection
     */
    public Connection getConnection() throws SQLException {
        return (connectionAddress != null)
                ? DriverManager.getConnection(connectionAddress)
                : dataSource.getConnection();
    }

    /**
     * This method closes SQL components: connections, statements, result sets.
     *
     * @param closeables list of AutoCloseable elements
     */
    public static void close(AutoCloseable ...closeables){
        try {
            for (AutoCloseable closeable : closeables) {
                if(closeable != null) {
                    closeable.close();
                }
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }


    /**
     * This method rollbacks connection if something go wrong
     *
     * @param connection pool
     */
    public static void rollback(Connection connection){
        try{
            if(connection != null) {
                connection.rollback();
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }


    /**
     * This method gets Specification as parameter, and counts the rows
     *
     * @param specification with query
     * @return count of rows
     *
     */
    public int count(Specification specification){

        int length = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = DBManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            statement = specification.getStatement(connection);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                length = resultSet.getInt(1);
            }

            connection.commit();
        }catch (SQLException exception){
            DBManager.rollback(connection);
        }finally {
            DBManager.close(resultSet, statement, connection);
        }

        return length;

    }

}
