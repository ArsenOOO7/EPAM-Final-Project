package com.arsen.epam.internet.shop.repository;

import com.arsen.epam.internet.shop.db.DBTest;
import com.arsen.epam.internet.shop.entity.Category;
import com.arsen.epam.internet.shop.entity.cart.Cart;
import com.arsen.epam.internet.shop.entity.cart.status.PurchaseStatus;
import com.arsen.epam.internet.shop.entity.product.Product;
import com.arsen.epam.internet.shop.entity.product.color.Color;
import com.arsen.epam.internet.shop.entity.product.size.SizeUnit;
import com.arsen.epam.internet.shop.entity.user.User;
import com.arsen.epam.internet.shop.entity.user.ban.UserBan;
import com.arsen.epam.internet.shop.entity.user.status.UserStatus;
import com.arsen.epam.internet.shop.repository.ban.IBanRepository;
import com.arsen.epam.internet.shop.repository.cart.ICartRepository;
import com.arsen.epam.internet.shop.repository.cart.specification.CartIdSpecification;
import com.arsen.epam.internet.shop.repository.category.ICategoryRepository;
import com.arsen.epam.internet.shop.repository.category.specification.CategoryAllSpecification;
import com.arsen.epam.internet.shop.repository.helper.Helper;
import com.arsen.epam.internet.shop.repository.product.IProductRepository;
import com.arsen.epam.internet.shop.repository.product.specification.ProductIdSpecification;
import com.arsen.epam.internet.shop.repository.user.IUserRepository;
import com.arsen.epam.internet.shop.repository.user.specification.UserIdSpecification;
import com.arsen.epam.internet.shop.repository.user.specification.UserLoginSpecification;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class RepositoryTest extends DBTest {

    @Test
    public void testUserAdd(){

        User user = Helper.createUser("Arsen", "Sydoryk", "arsen.sydoryk@gmail.com", "ArsenOOO87",
                "mysecretpassword123ABC", Date.valueOf("2004-03-15"), 0.0, 0, UserStatus.USER);

        IUserRepository repository = HeadRepository.getUserRepository();
        repository.save(user);

        assertEquals(1, user.getId());

        User user2 = Helper.createUser("Andrii", "Sovtus", "andrii.sovuts@gmail.com", "Mesnuk",
                "mysecretpassword123ABC", Date.valueOf("2004-03-16"), 0.0, 0, UserStatus.USER);

        repository.save(user2);

        assertEquals(2, user2.getId());

    }


    @Test
    public void testUserUpdate(){

        User user = Helper.createUser("Arsen", "Sydoryk", "arsen.sydoryk@gmail.com", "ArsenOOO87",
                "mysecretpassword123ABC", Date.valueOf("2004-03-15"), 0.0, 0, UserStatus.USER);

        IUserRepository repository = HeadRepository.getUserRepository();
        repository.save(user);

        assertEquals(1, user.getId());

        user.setName(user.getName() + " hero");
        user.increaseBalance(1000);
        user.setAvatarId(10);
        user.setStatus(UserStatus.ADMIN);

        repository.update(user);
        User check = repository.findOne(new UserIdSpecification(user.getId()));

        assertNotNull(check);
        assertEquals(user.getName(), check.getName());

        assertEquals(1000.0, check.getBalance());
        assertEquals(10, check.getAvatarId());
        assertEquals(UserStatus.ADMIN, check.getStatus());

    }


    @Test
    public void testFindUser(){

        User user = Helper.createUser("Arsen", "Sydoryk", "arsen.sydoryk@gmail.com", "ArsenOOO87",
                "mysecretpassword123ABC", Date.valueOf("2004-03-15"), 0.0, 0, UserStatus.USER);

        IUserRepository repository = HeadRepository.getUserRepository();
        repository.save(user);

        assertEquals(1, user.getId());

        //Find by login
        UserLoginSpecification loginSpecification = new UserLoginSpecification();
        loginSpecification.setLogin(user.getLogin());

        User check = repository.findOne(loginSpecification);

        assertNotNull(check);
        assertEquals(1, check.getId());


        //Find by email
        loginSpecification.clear();
        loginSpecification.setEmail(user.getEmail());

        check = repository.findOne(loginSpecification);
        assertNotNull(check);
        assertEquals(1, check.getId());

        //Find by login & password

        loginSpecification.clear();
        loginSpecification.setLogin(user.getLogin());
        loginSpecification.setPassword(user.getPassword());

        check = repository.findOne(loginSpecification);
        assertNotNull(check);
        assertEquals(1, check.getId());

    }


    @Test
    public void testBan(){

        User user = Helper.createUser("Arsen", "Sydoryk", "arsen.sydoryk@gmail.com", "ArsenOOO87",
                "mysecretpassword123ABC", Date.valueOf("2004-03-15"), 0.0, 0, UserStatus.USER);

        IUserRepository userRepository = HeadRepository.getUserRepository();
        IBanRepository banRepository = HeadRepository.getBanRepository();

        userRepository.save(user);

        assertEquals(1, user.getId());

        UserBan ban = Helper.createBan(user.getId(), "Test reason", 5);
        banRepository.save(ban);

        assertEquals(1, ban.getId());

        User check = userRepository.findOne(new UserIdSpecification(user.getId()));

        assertTrue(check.isBanned());
        assertEquals(ban.getReason(), check.getUserBan().getReason());

    }

    @Test
    public void testDeleteUser(){

        User user = Helper.createUser("Arsen", "Sydoryk", "arsen.sydoryk@gmail.com", "ArsenOOO87",
                "mysecretpassword123ABC", Date.valueOf("2004-03-15"), 0.0, 0, UserStatus.USER);

        IUserRepository userRepository = HeadRepository.getUserRepository();
        userRepository.save(user);
        userRepository.delete(user);

        User check = userRepository.findOne(new UserIdSpecification(user.getId()));
        assertNull(check);

    }


    @Test
    public void testCategory(){

        Category category = Helper.createCategory("laptop", "Ноутбуки", "Laptops");
        ICategoryRepository categoryRepository = HeadRepository.getCategoryRepository();

        categoryRepository.save(category);
        assertEquals(1, category.getId());

        //Check if exists
        Category check = categoryRepository.findOne(new CategoryAllSpecification(category.getId()));
        assertNotNull(check);

        //Update
        category.setIdentifier("laptops");
        categoryRepository.update(category);

        check = categoryRepository.findOne(category.getId());
        assertNotNull(check);

        assertEquals(category.getIdentifier(), check.getIdentifier());

        //Delete
        categoryRepository.delete(category);

        check = categoryRepository.findOne(category.getId());
        assertNull(check);

    }


    @Test
    public void testProduct(){

        IProductRepository productRepository = HeadRepository.getProductRepository();
        ICategoryRepository categoryRepository = HeadRepository.getCategoryRepository();

        //Category must be here because of foreign key
        Category category = Helper.createCategory("laptop", "Ноутбуки", "Laptops");
        categoryRepository.save(category);

        Product product = Helper.createProduct("HP EliteBook", "HP EliteBook 0219",
                "Best laptop for working", 100, 500, Color.BLACK, SizeUnit.CENTIMETRE, 50,
                category.getId(), 0, 0, 0);

        productRepository.save(product);
        assertEquals(1, product.getId());

        //Check if exists
        Product check = productRepository.findOne(new ProductIdSpecification(product.getId()));
        assertNotNull(check);

        //Try to update
        product.setPrice(600);
        product.setPreviewImageId(1);

        productRepository.update(product);

        check = productRepository.findOne(new ProductIdSpecification(product.getId()));
        assertNotNull(check);

        assertEquals(600, check.getPrice());
        assertEquals(1, check.getPreviewImageId());


        //Deleting
        productRepository.delete(product.getId());

        check = productRepository.findOne(new ProductIdSpecification(product.getId()));
        assertNull(check);

    }


    @Test
    public void testCart(){

        IUserRepository userRepository = HeadRepository.getUserRepository();
        IProductRepository productRepository = HeadRepository.getProductRepository();
        ICategoryRepository categoryRepository = HeadRepository.getCategoryRepository();
        ICartRepository cartRepository = HeadRepository.getCartRepository();

        User user = Helper.createUser("Arsen", "Sydoryk", "arsen.sydoryk@gmail.com", "ArsenOOO87",
                "mysecretpassword123ABC", Date.valueOf("2004-03-15"), 0.0, 0, UserStatus.USER);
        Category category = Helper.createCategory("laptop", "Ноутбуки", "Laptops");

        categoryRepository.save(category);

        Product product = Helper.createProduct("HP EliteBook", "HP EliteBook 0219",
                "Best laptop for working", 100, 500, Color.BLACK, SizeUnit.CENTIMETRE, 50,
                category.getId(), 0, 0, 0);

        userRepository.save(user);
        productRepository.save(product);

        //Price is 0 because database (cart_view) calculates price if the status is CART
        //You will see it in next tests
        Cart cart = Helper.createCart(user.getId(), product.getId(), 10, 0, PurchaseStatus.CART);

        cartRepository.save(cart);
        assertEquals(1, cart.getId());

        Cart check = cartRepository.findOne(new CartIdSpecification(cart.getId()));
        //Check cart price, not 0!
        assertEquals(cart.getAmount() * product.getPrice(), check.getPrice());

        //Now lets change product price per one, and check
        product.setPrice(product.getPrice() - 10);
        productRepository.update(product);

        check = cartRepository.findOne(new CartIdSpecification(cart.getId()));
        //Price will be changed
        assertEquals(cart.getAmount() * product.getPrice(), check.getPrice());

        //Now I will change status to PURCHASED
        cart.setStatus(PurchaseStatus.PURCHASED);
        cart.setPrice(check.getPrice()); //To update price

        cartRepository.update(cart);

        //Lets change product price again
        product.setPrice(product.getPrice() + 10);
        productRepository.update(product);

        check = cartRepository.findOne(new CartIdSpecification(cart.getId()));
        assertNotEquals(check.getAmount() * product.getPrice(), check.getPrice());

        //But it's not similar if I cancel it
        cart.setStatus(PurchaseStatus.CANCELLED);
        cartRepository.update(cart);


        check = cartRepository.findOne(new CartIdSpecification(cart.getId()));
        assertEquals(check.getAmount() * product.getPrice(), check.getPrice());

        //Now I will delete only category, so product and cart must also be deleted
        categoryRepository.delete(category);

        Product checkProduct = productRepository.findOne(new ProductIdSpecification(product.getId()));
        assertNull(checkProduct);

        check = cartRepository.findOne(new CartIdSpecification(cart.getId()));
        assertNull(check);

    }



}
