package com.arsen.epam.internet.shop.entity.image;

import com.arsen.epam.internet.shop.entity.product.Product;
import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.repository.HeadRepository;
import com.arsen.epam.internet.shop.repository.image.IImageRepository;
import com.arsen.epam.internet.shop.repository.image.specification.ImageIdSpecification;
import com.arsen.epam.internet.shop.service.generator.Generator;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 *
 * Class that upload/create/print Image
 *
 */
public class ImageLoader {

    private static final Logger log = LogManager.getLogger(ImageLoader.class);


    /**
     * Method creates image for user
     *
     * @param part with image
     * @param user object
     */
    public static void createImage(Part part, User user){
        try {
            log.debug("Creating image for user (" + user.getId() + ")");
            user.setAvatarId(createImage(part));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method creates image for product
     *
     * @param part with image
     * @param product object
     */
    public static void createImage(Part part, Product product){
        try {
            log.debug("Creating image for product (" + product.getId() + ")");
            product.setPreviewImageId(createImage(part));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method creates image and uploads it do DB
     *
     * @param part with image
     * @return id of image
     * @throws IOException
     *  if something's wrong with InputStream
     */
    private static int createImage(Part part) throws IOException {

        String uuid = Generator.getRandomString();

        Image image = new Image();
        image.setContentType(part.getContentType());
        image.setUuid(uuid);
        image.setData(part.getInputStream());

        IImageRepository repository = HeadRepository.getImageRepository();
        repository.save(image);

        log.trace("ID: " + image.getId());
        log.trace("Content type: " + image.getContentType().getContentType());
        log.trace("Extension: " + image.getContentType().getExtension());
        log.trace("UUID: " + image.getUuid());

        return image.getId();

    }


    /**
     * Method updates image for product
     *
     * @param part with image
     * @param user object
     */
    public static void updateImage(Part part, User user){
        try {
            log.debug("Attempt to update user's avatar");
            if(!updateImage(part, user.getAvatarId())){
                createImage(part, user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method updates image for product
     *
     * @param part with image
     * @param product object
     */
    public static void updateImage(Part part, Product product){
        try {
            log.debug("Attempt to update product's preview image");
            if(!updateImage(part, product.getPreviewImageId())){
                createImage(part, product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method updates image
     *
     * @param part with new image
     * @param image_id of row in table
     * @return true if image successfully updated;
     *          false if image is null or entity have not had image yet
     * @throws IOException
     *  if something's wrong with InputStream
     */
    private static boolean updateImage(Part part, int image_id) throws IOException {

        if(image_id == 0){
            return false;
        }

        log.debug("Continue updating...");
        IImageRepository repository = HeadRepository.getImageRepository();

        log.debug("Getting image...");
        Image image = repository.findOne(new ImageIdSpecification(image_id));
        if(image == null){
            return false;
        }

        log.debug("Updating stream data");
        image.setData(part.getInputStream());

        log.debug("Updating image in DB");
        repository.update(image);
        return true;
    }


    /**
     * Method prints image on the screen
     *
     * @param response servlet
     * @param image_id image id
     * @throws SQLException
     *  if something go wrong while extracting image from DB
     * @throws IOException
     *  if something go wrong with OutputStream
     */
    public static void printImage(HttpServletResponse response, int image_id) throws SQLException, IOException {

        log.debug("Attempt to print image");

        Image image = HeadRepository.getImageRepository()
                .findOne(new ImageIdSpecification(image_id));

        log.debug("Setting content type to Response: " + image.getContentType().getContentType());
        response.setContentType(image.getContentType().getContentType());

        log.debug("Creating streams' instance");
        try(InputStream stream = image.getData();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(stream);
            ServletOutputStream servletOutputStream = response.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(servletOutputStream)){

            log.debug("Writing bytes...");
            int ch;
            while((ch = bufferedInputStream.read()) != -1){
                bufferedOutputStream.write(ch);
            }
            log.debug("Image was printed");
        }

    }

}
