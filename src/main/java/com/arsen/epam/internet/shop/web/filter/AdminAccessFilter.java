package com.arsen.epam.internet.shop.web.filter;


import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.entity.user.status.UserStatus;
import com.arsen.epam.internet.shop.web.path.WebPath;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Admin access filter. Security
 *
 * @author Arsen Sydoryk
 */
@WebFilter(urlPatterns = {"/product/add", "/product/edit/*", "/product/delete/*", "/category/*", "/admin/*"})
public class AdminAccessFilter extends HttpFilter {

    private static final Logger log = LogManager.getLogger(AdminAccessFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        log.debug("Filter started");
        User user = (User) req.getSession().getAttribute("user");
        if(user == null || user.getStatus() != UserStatus.ADMIN){
            log.trace("Attempt to go to the authorization page by non-admin");
            res.sendRedirect(WebPath.HOME);
        }else {
            log.debug("Filter finished");
            chain.doFilter(req, res);
        }
    }

}
