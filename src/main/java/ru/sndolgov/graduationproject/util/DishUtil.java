package ru.sndolgov.graduationproject.util;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sndolgov.graduationproject.model.Dish;
import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.service.restaurant.RestaurantService;
import ru.sndolgov.graduationproject.to.DishTo;

import java.util.List;

/**
 * Created by Сергей on 17.03.2018.
 */
public class DishUtil {

    @Autowired
    private RestaurantService restaurantService;

    public static DishTo asTo(Integer menuId, Dish dish, List <Dish> menuDishes) {
        return new DishTo(dish.getId(), menuId, dish.getDescription(), dish.getPrice(), isInMenu(dish, menuDishes));
    }

    private static boolean isInMenu(Dish dish, List <Dish> menuDishes) {
        return menuDishes.contains(dish);
    }

    public static DishTo asToAll(Integer restaurantId, Dish dish) {
        return new DishTo(dish.getId(), restaurantId, dish.getDescription(), dish.getPrice(), dish.isEnabled());
    }

}
