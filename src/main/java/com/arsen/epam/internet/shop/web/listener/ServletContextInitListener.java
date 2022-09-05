package com.arsen.epam.internet.shop.web.listener;

import com.arsen.epam.internet.shop.database.DBConnection;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ServletContextInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DBConnection.getInstance();
    }
}
