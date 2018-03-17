package ru.sndolgov.graduationproject.service.dish;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.sndolgov.graduationproject.RestaurantTestData;
import ru.sndolgov.graduationproject.model.Dish;
import ru.sndolgov.graduationproject.service.AbstractServiceTest;
import ru.sndolgov.graduationproject.util.exception.NotFoundException;

import java.awt.*;

import static org.junit.Assert.*;
import static ru.sndolgov.graduationproject.DishTestData.*;
import static ru.sndolgov.graduationproject.DishTestData.assertMatch;
import static ru.sndolgov.graduationproject.RestaurantTestData.RESTAURANT1;
import static ru.sndolgov.graduationproject.RestaurantTestData.RESTAURANT1_ID;
import static ru.sndolgov.graduationproject.RestaurantTestData.RESTAURANT2_ID;

/**
 * Created by Сергей on 14.03.2018.
 */
public class DishServiceImplTest extends AbstractServiceTest {
    @Autowired
    DishService service;

    @Test
    public void delete() throws Exception {
        service.delete(DISH1_ID);
        assertMatch(service.getAllByRestaurant(RESTAURANT1_ID), DISH2,DISH3,DISH4,DISH5,DISH6);
    }

    @Test
    public void get() throws Exception {
        Dish dish = service.get(DISH1_ID, RESTAURANT1_ID);
        assertMatch(DISH1, dish);
    }

    @Test
    public void getWithRestaurant() throws Exception {
        Dish dish = service.getWithRestaurant(DISH1_ID, RESTAURANT1_ID);
        assertMatch(DISH1, dish);
        RestaurantTestData.assertMatch(dish.getRestaurant(), RESTAURANT1);
    }

    @Test (expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        Dish dish = service.get(DISH1_ID, RESTAURANT2_ID);
        assertMatch(DISH1, dish);
    }

    @Test
    public void create() throws Exception {
        Dish newDish = service.create(getCreated(), RESTAURANT1_ID);
        assertMatch(service.getAllByRestaurant(RESTAURANT1_ID), DISH1,DISH2,DISH3,DISH4,DISH5,DISH6, newDish);
    }

    @Test (expected = DataAccessException.class)
    public void creatDuplicate() throws Exception {
        Dish newDish = getCreated();
        newDish.setDescription(DISH1.getDescription());
        service.create(newDish, RESTAURANT1_ID);
    }

    @Test
    public void update() throws Exception {
        Dish updatedDish = service.update(getUpdated(), RESTAURANT1_ID);
        assertMatch(service.getAllByRestaurant(RESTAURANT1_ID), updatedDish,DISH2,DISH3,DISH4,DISH5,DISH6);
    }

      @Test
    public void getAllByRestaurant() throws Exception {
        assertMatch(service.getAllByRestaurant(RESTAURANT1_ID), DISH1,DISH2,DISH3,DISH4,DISH5,DISH6);
    }
}