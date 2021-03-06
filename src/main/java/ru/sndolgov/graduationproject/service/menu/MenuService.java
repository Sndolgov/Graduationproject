package ru.sndolgov.graduationproject.service.menu;

import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.to.MenuTo;
import ru.sndolgov.graduationproject.util.exception.NotFoundException;

import java.util.Date;
import java.util.List;

/**
 * Created by Сергей on 07.02.2018.
 */
public interface MenuService {

    Menu create(Menu menu, int restaurantId);

    Menu update(Menu menu, int restaurantId);

    Menu update(MenuTo menuTo);

    Menu get(int id, int restaurantId) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Menu> getAll(int restaurantId);

    Menu getWithRestaurant(int id) throws NotFoundException;

    Menu getWithDishes(int id, int restaurantId)throws NotFoundException;

    List<Menu> getAllByDate(Date date);

    void addDish (int menuId, int dishId, int restaurantId);

    void deleteDish(int menuId, int dishId, int restaurantId);

}
