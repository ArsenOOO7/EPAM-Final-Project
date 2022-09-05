package com.arsen.epam.internet.shop.web.controller.image;

import com.arsen.epam.internet.shop.entity.image.ImageLoader;
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
import java.sql.SQLException;

/**
 * Image controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/image/*")
public class ImageController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ImageController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_GET);
        log.trace(LogMessage.EXTRACTING_ID);

        int id = IdValidator.getPathId(req);
        if (id <= 0) {
            log.error("Error: invalid id (" + id + ")");
            resp.sendRedirect(WebPath.HOME);
            return;
        }

        try {
            log.trace("Printing image (ID: " + id + ")");
            ImageLoader.printImage(resp, id);
        } catch (SQLException e) {
            log.error(e);
        }

        log.debug(LogMessage.CONTROLLER_FINISHED);
    }
}
