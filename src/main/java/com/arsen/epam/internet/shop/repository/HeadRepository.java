package com.arsen.epam.internet.shop.repository;

import com.arsen.epam.internet.shop.repository.ban.BanRepository;
import com.arsen.epam.internet.shop.repository.ban.IBanRepository;
import com.arsen.epam.internet.shop.repository.cart.CartRepository;
import com.arsen.epam.internet.shop.repository.cart.ICartRepository;
import com.arsen.epam.internet.shop.repository.category.CategoryRepository;
import com.arsen.epam.internet.shop.repository.category.ICategoryRepository;
import com.arsen.epam.internet.shop.repository.image.IImageRepository;
import com.arsen.epam.internet.shop.repository.image.ImageRepository;
import com.arsen.epam.internet.shop.repository.product.IProductRepository;
import com.arsen.epam.internet.shop.repository.product.ProductRepository;
import com.arsen.epam.internet.shop.repository.user.IUserRepository;
import com.arsen.epam.internet.shop.repository.user.UserRepository;

/**
 * Repository of all repositories
 *
 * @author Arsen Sydoryk
 */
public class HeadRepository {

    private static IUserRepository userRepository;

    private static IProductRepository productRepository;

    private static IImageRepository imageRepository;

    private static ICartRepository cartRepository;

    private static IBanRepository banRepository;

    private static ICategoryRepository categoryRepository;


    public static synchronized IUserRepository getUserRepository(){
        if(userRepository == null){
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public static synchronized IProductRepository getProductRepository(){
        if(productRepository == null){
            productRepository = new ProductRepository();
        }
        return productRepository;
    }

    public static synchronized IImageRepository getImageRepository(){
        if(imageRepository == null){
            imageRepository = new ImageRepository();
        }
        return imageRepository;
    }

    public static synchronized ICartRepository getCartRepository(){
        if(cartRepository == null){
            cartRepository = new CartRepository();
        }
        return cartRepository;
    }

    public static synchronized IBanRepository getBanRepository(){
        if(banRepository == null){
            banRepository = new BanRepository();
        }
        return banRepository;
    }

    public static synchronized ICategoryRepository getCategoryRepository(){
        if(categoryRepository == null){
            categoryRepository = new CategoryRepository();
        }
        return categoryRepository;
    }
}
