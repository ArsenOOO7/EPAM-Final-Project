package com.arsen.epam.internet.shop.web.filter;

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
 * User access filter. Security
 *
 * @author Arsen Sydoryk
 */
@WebFilter(urlPatterns = {"/profile", "/user/*", "/cart/*"})
public class UserAccessFilter extends HttpFilter {

    private static final Logger log = LogManager.getLogger(UserAccessFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        log.debug("Filter started");
        if(req.getSession().getAttribute("user") == null){
            log.trace("Attempt to go to user pages by anonymouss");
            res.sendRedirect(WebPath.HOME);
        }else {
            chain.doFilter(req, res);
        }
        log.debug("Filter finished");
    }
}
