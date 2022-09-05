package com.arsen.epam.internet.shop.web.controller.category;

import com.arsen.epam.internet.shop.entity.Category;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.service.validation.bean.CategoryValidation;
import com.arsen.epam.internet.shop.service.validation.id.IdValidator;
import com.arsen.epam.internet.shop.web.path.Views;
import com.arsen.epam.internet.shop.web.path.WebPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Editing category controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/category/edit/*")
public class EditCategoryController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(EditCategoryController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_GET);
        log.trace(LogMessage.EXTRACTING_ID);

        int id = IdValidator.getPathId(req);
        if (id <= 0) {
            log.error("Error: invalid id (" + id + ")");
            resp.sendRedirect(WebPath.CATEGORIES);
            return;
        }

        log.trace("Getting the category by id (ID: " + id + ")s");
        Category category = HeadRepository.getCategoryRepository().findOne(id);

        log.trace("Setting category to RequestScope");
        req.setAttribute("category", category);

        log.debug(LogMessage.CONTROLLER_FINISHED);
        req.getRequestDispatcher(Views.CATEGORY_EDIT).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_POST);
        log.trace(LogMessage.EXTRACTING_ID);

        int id = IdValidator.getPathId(req);
        if (id <= 0) {
            log.error("Error: invalid id (" + id + ")");
            resp.sendRedirect(WebPath.CATEGORIES);
            return;
        }

        String message = CategoryValidation.validateCategory(req);
        if(!message.isEmpty()){
            req.getSession().setAttribute("category_error", message);
            log.error("Error code: " + message);
            resp.sendRedirect(WebPath.CATEGORIES_ADD);
            return;
        }


        String identifier = req.getParameter("identifier");
        String localeUA = req.getParameter("locale_ua");
        String localeEN = req.getParameter("locale_en");

        log.trace("Identifier: " + identifier);
        log.trace("Locale UA: " + localeUA);
        log.trace("Locale EN: " + localeEN);

        log.trace("Updating category in DB");

        Category category = HeadRepository.getCategoryRepository().findOne(id);


        category.setIdentifier(identifier);
        category.setLocaleUA(localeUA);
        category.setLocaleEN(localeEN);

        HeadRepository.getCategoryRepository().update(category);

        log.debug(LogMessage.CONTROLLER_FINISHED);
        resp.sendRedirect(WebPath.CATEGORIES);

    }
}
