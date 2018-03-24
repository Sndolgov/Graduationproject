package ru.sndolgov.graduationproject.service.dish;

import ru.sndolgov.graduationproject.model.Dish;
import ru.sndolgov.graduationproject.to.DishTo;

import java.util.List;

/**
 * Created by Сергей on 14.03.2018.
 */
public interface DishService {
    void delete(int id);

    Dish get(int id, int restaurantId);

    Dish create(Dish dish, int restaurantId);

    Dish update(Dish dish, int restaurantId);

    Dish update(DishTo menuTo);

    List<Dish> getAllByRestaurant(int restaurantId);

}
