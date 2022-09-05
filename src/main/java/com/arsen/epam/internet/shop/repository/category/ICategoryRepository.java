package com.arsen.epam.internet.shop.repository.category;

import com.arsen.epam.internet.shop.entity.Category;
import com.arsen.epam.internet.shop.repository.Repository;

public interface ICategoryRepository extends Repository<Category> {

    /**
     * If there is a list of categories in cache, it will take from there, otherwise from DB
     *
     * @param id of entity
     * @return Category
     */
    Category findOne(int id);

}
