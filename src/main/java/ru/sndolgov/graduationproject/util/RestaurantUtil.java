package ru.sndolgov.graduationproject.util;

import ru.sndolgov.graduationproject.model.Dish;
import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.model.Restaurant;
import ru.sndolgov.graduationproject.to.RestaurantTo;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Created by Сергей on 07.02.2018.
 */
public class RestaurantUtil {

    public static RestaurantTo asTo(Menu menu) {
        List<Dish> dishes = menu.getDishes();

        return new RestaurantTo(menu.getRestaurant().getId(), menu.getId(), menu.getRestaurant().getName(), menu.getDescription(),
                getDishDescription(dishes), getDishPrice(dishes), getTotalValue(dishes), menu.getVoices().size());
    }

    private static String getDishDescription(List<Dish> dishes) {
        StringJoiner dishDescription = new StringJoiner("<br>");
        dishes.forEach(d -> dishDescription.add(d.getDescription()));
        return dishDescription.toString();
    }

    private static String getDishPrice(List<Dish> dishes) {
        StringJoiner dishPrice = new StringJoiner("<br>");
        dishes.forEach(d -> dishPrice.add(d.getPrice().toString()));
        return dishPrice.toString();
    }

    private static Integer getTotalValue(List<Dish> dishes) {
        Integer totalValue = 0;
        for (Dish dish : dishes)
            totalValue += dish.getPrice();
        return totalValue;
    }
}
