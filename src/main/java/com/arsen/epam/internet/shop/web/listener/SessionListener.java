package com.arsen.epam.internet.shop.web.listener;

import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.repository.user.UserRepository;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * Session listener
 *
 * @author Arsen Sydoryk
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        if(se.getSession().getAttribute("user") != null){
            User user = (User) se.getSession().getAttribute("user");
            new UserRepository().update(user);
        }

    }
}


