package com.arsen.epam.internet.shop.db;

import com.arsen.epam.internet.shop.database.DBManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBTest {

    public static final String DB_REMOVE = "DROP SCHEMA IF EXISTS internet_shop_test";

    public static final String DB_CONNECT = "jdbc:h2:mem:internet_shop_test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;MODE=MYSQL";
    public static final String DRIVER = "org.h2.Driver";

    private static final String SQL_FILE = "db/new_internet_shop_test.sql";

    private static Connection connection;

    private static List<String> queries;

    @BeforeAll
    public static void setUpDB() throws IOException, SQLException {

        connection = DBManager.getInstance(DRIVER, DB_CONNECT).getConnection();
        readQueries();

    }


    private static void readQueries() throws IOException {
        List<String> lines = Files.readAllLines(Path.of(SQL_FILE));
        queries = List.of(String.join("", lines).split(";"));
    }


    @AfterAll
    public static void clearConnection() throws SQLException {
        connection.close();
    }

    @BeforeEach
    public void setUpTables() throws SQLException {

        for (String query : queries) {
            connection.createStatement().executeUpdate(query);
        }

    }


    @AfterEach
    public void clearTables() throws SQLException {
        connection.createStatement().executeUpdate(DB_REMOVE);
//        connection.close();
    }

}
