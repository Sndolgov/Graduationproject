package ru.sndolgov.graduationproject.repository.restaurant;

import ru.sndolgov.graduationproject.model.Restaurant;
import ru.sndolgov.graduationproject.model.User;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    Restaurant getByName(String name);

    List<Restaurant> getAll();


}
