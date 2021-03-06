package ru.sndolgov.graduationproject.service.restaurant;

import ru.sndolgov.graduationproject.model.Restaurant;
import ru.sndolgov.graduationproject.util.exception.NotFoundException;
import java.util.List;

public interface RestaurantService {

        Restaurant create(Restaurant restaurant);

        void delete(int id) throws NotFoundException;

        Restaurant get(int id) throws NotFoundException;

        void update(Restaurant restaurant);

        List<Restaurant> getAll();

        void enable(int id, boolean enable);

        Restaurant getWithAllFields(int id);

        Restaurant getWithDishes(int restaurantId);
}
