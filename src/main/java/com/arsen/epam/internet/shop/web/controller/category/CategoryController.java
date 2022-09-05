package com.arsen.epam.internet.shop.web.controller.category;

import com.arsen.epam.internet.shop.entity.Category;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.category.specification.CategoryAllSpecification;
import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.web.path.Views;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * Show all categories controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/category")
public class CategoryController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(CategoryController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug(LogMessage.CONTROLLER_GET);
        log.trace("Getting list of categories...");
        List<Category> categories = HeadRepository.getCategoryRepository().findAll(new CategoryAllSpecification());

        log.trace("Setting list of categories to RequestScope");
        req.setAttribute("categories", categories);

        log.debug(LogMessage.CONTROLLER_FINISHED);
        req.getRequestDispatcher(Views.CATEGORY).forward(req, resp);
    }
}
