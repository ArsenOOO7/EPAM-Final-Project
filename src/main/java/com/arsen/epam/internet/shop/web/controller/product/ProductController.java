package com.arsen.epam.internet.shop.web.controller.product;

import com.arsen.epam.internet.shop.database.DBConnection;
import com.arsen.epam.internet.shop.entity.Category;
import com.arsen.epam.internet.shop.entity.product.Product;
import com.arsen.epam.internet.shop.entity.product.color.Color;
import com.arsen.epam.internet.shop.entity.product.sort.Sort;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.category.specification.CategoryAllSpecification;
import com.arsen.epam.internet.shop.repository.product.specification.ProductAllSpecification;
import com.arsen.epam.internet.shop.service.data.Data;
import com.arsen.epam.internet.shop.service.log.LogMessage;
import com.arsen.epam.internet.shop.service.utils.Utils;
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
 * Show all products controller
 *
 * @author Arsen Sydoryk
 */
@WebServlet("/product")
public class ProductController extends HttpServlet {

    private static final Logger log = LogManager.getLogger(ProductController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.debug(LogMessage.CONTROLLER_GET);

        log.trace("Getting list of categories...");
        List<Category> categories = HeadRepository.getCategoryRepository().findAll(new CategoryAllSpecification());

        log.trace("Setting list of categories to RequestScope...");
        req.setAttribute("categories", categories);
        log.trace("Setting list of colors to RequestScope...");
        req.setAttribute("colors", Color.values());

        log.trace(LogMessage.EXTRACTING_PAGE);
        String page = req.getParameter("page");
        int pageNumber = 1;

        if(page != null && !page.isEmpty()){
            pageNumber = Utils.getInt(page);
        }

        log.trace("Creating specification of products");
        ProductAllSpecification specification = getProducts(req, pageNumber);

        log.trace("Getting list of products");
        List<Product> products = HeadRepository.getProductRepository().findAll(specification);

        log.trace("Building query line");
        String queryLine = Utils.buildQueryLine(req);

        log.trace("Setting query line to RequestScope");
        req.setAttribute("query", queryLine);
        log.trace("Setting list of products to RequestScope");
        req.setAttribute("list", products);
        log.trace("Setting current page to RequestScope");
        req.setAttribute("page", pageNumber);

        log.trace("Getting and setting number of all pages by this query into RequestScope");
        specification.setSelect("COUNT(*)");
        req.setAttribute("pages", (int) Math.ceil(DBConnection.getInstance().count(specification)
                / (double) Data.MAX_ENTITIES_PAGE));

        log.debug(LogMessage.CONTROLLER_FINISHED);
        req.getRequestDispatcher(Views.PRODUCT_SEARCH).forward(req, resp);
    }

    /**
     *
     * @param req http request
     * @param page current page
     * @return specification
     */
    private ProductAllSpecification getProducts(HttpServletRequest req, int page){

        ProductAllSpecification productAllSpecification = new ProductAllSpecification();

        if(req.getParameter("search_product") != null) {
            productAllSpecification.setSearch(req.getParameter("search_product"));
        }

        if(req.getParameter("min_price") != null) {
            String minPrice = req.getParameter("min_price");
            productAllSpecification.setMinPrice(Utils.getInt(minPrice));
        }

        if(req.getParameter("max_price") != null) {
            String maxPrice = req.getParameter("max_price");
            productAllSpecification.setMaxPrice(Utils.getInt(maxPrice));
        }

        if(req.getParameter("category") != null) {
            String category = req.getParameter("category");
            productAllSpecification.setCategory(Utils.getCategoryId(category));
        }

        if(req.getParameter("color") != null) {
            String color = req.getParameter("color");
            productAllSpecification.setColor(Color.getInstance(color).getId());
        }

        if(req.getParameter("min_size") != null) {
            String minSize = req.getParameter("min_size");
            productAllSpecification.setMinSize(Utils.getInt(minSize));
        }

        if(req.getParameter("max_size") != null) {
            String maxSize = req.getParameter("max_size");
            productAllSpecification.setMinSize(Utils.getInt(maxSize));
        }


        if(req.getParameter("sort") != null){
            Sort sort = Sort.getInstance(req.getParameter("sort"));
            productAllSpecification.setSort(sort);
        }

        if(page > 1){
            productAllSpecification.setPage(page);
        }

        return productAllSpecification;
    }

}
