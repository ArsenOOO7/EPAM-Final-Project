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
 * Auth access filter. Security
 *
 * @author Arsen Sydoryk
 */
@WebFilter(urlPatterns = {"/login", "/register"})
public class AuthAccessFilter extends HttpFilter {

    private static final Logger log = LogManager.getLogger(AuthAccessFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        log.debug("Filter started");
        if(req.getSession().getAttribute("user") != null){
            log.trace("Attempt to go to the page by non-signed-in user");
            res.sendRedirect(WebPath.HOME);
        }else {
            log.debug("Filter finished");
            chain.doFilter(req, res);
        }
    }
}
