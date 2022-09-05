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

    public static IUserRepository getUserRepository(){
        return new UserRepository();
    }

    public static IProductRepository getProductRepository(){
        return new ProductRepository();
    }

    public static IImageRepository getImageRepository(){
        return new ImageRepository();
    }

    public static ICartRepository getCartRepository(){
        return new CartRepository();
    }

    public static IBanRepository getBanRepository(){
        return new BanRepository();
    }

    public static ICategoryRepository getCategoryRepository(){
        return new CategoryRepository();
    }
}
