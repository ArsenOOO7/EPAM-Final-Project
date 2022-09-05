package com.arsen.epam.internet.shop.web.controller.category;

import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.service.validation.id.IdValidator;
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
 * Deleting category controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/category/delete/*")
public class DeleteCategoryController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(DeleteCategoryController.class);

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

        log.trace("Deleting the category...");
        HeadRepository.getCategoryRepository().delete(id);

        log.debug(LogMessage.CONTROLLER_FINISHED);
        resp.sendRedirect(WebPath.CATEGORIES);

    }
}
