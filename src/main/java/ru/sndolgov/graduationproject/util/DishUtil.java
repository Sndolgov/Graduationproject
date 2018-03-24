package ru.sndolgov.graduationproject.util;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sndolgov.graduationproject.model.Dish;
import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.service.restaurant.RestaurantService;
import ru.sndolgov.graduationproject.to.DishTo;
import ru.sndolgov.graduationproject.to.MenuTo;

import java.util.List;

/**
 * Created by Сергей on 17.03.2018.
 */
public class DishUtil {

    @Autowired
    private RestaurantService restaurantService;

    public static DishTo asToIncluded(Integer menuId, Dish dish, List <Dish> menuDishes) {
        return new DishTo(dish.getId(), menuId, dish.getDescription(), dish.getPrice(), isInMenu(dish, menuDishes));
    }

    public static DishTo asTo(Integer restaurantId, Dish dish) {
        return new DishTo(dish.getId(), restaurantId, dish.getDescription(), dish.getPrice(), dish.isEnabled());
    }

    private static boolean isInMenu(Dish dish, List <Dish> menuDishes) {
        return menuDishes.contains(dish);
    }


    public static Dish createNewFromTo(DishTo dishTo) {
        return new Dish(null, dishTo.getDescription(), dishTo.getPrice(), true);
    }

    public static Dish updateFromTo(Dish dish, DishTo dishTo) {
        dish.setDescription(dishTo.getDescription());
        dish.setPrice(dishTo.getPrice());
        return dish;
    }

}
