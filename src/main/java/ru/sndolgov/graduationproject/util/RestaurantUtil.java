package ru.sndolgov.graduationproject.util;

import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.model.Restaurant;
import ru.sndolgov.graduationproject.to.RestaurantTo;

/**
 * Created by Сергей on 07.02.2018.
 */
public class RestaurantUtil {

    public static RestaurantTo asTo (Menu menu){
        return new RestaurantTo(menu.getRestaurant().getId(), menu.getRestaurant().getName(), menu.getDescription());
    }
}
