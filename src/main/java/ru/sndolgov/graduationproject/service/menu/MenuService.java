package ru.sndolgov.graduationproject.service.menu;

import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.util.exception.NotFoundException;

import java.util.Date;
import java.util.List;

/**
 * Created by Сергей on 07.02.2018.
 */
public interface MenuService {

    Menu creat(Menu menu, int restaurantId);

    Menu update(Menu menu, int restaurantId) throws NotFoundException;

    Menu get(int id, int restaurantId) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Menu> getAll(int restaurantId);

    Menu getWithRestaurant(int id);

    Menu getWithRestaurantAndDishes(int id, int restaurantId);

    Menu getWithDishes(int id, int restaurantId);

    List<Menu> getAllByDate(Date date);

    Menu getWithVoices(int id, int restaurantId);

    Menu getWithDishesVoices(int id, int restaurantId);

    Menu addDish (int id, int dishId, int restaurantId);
}
