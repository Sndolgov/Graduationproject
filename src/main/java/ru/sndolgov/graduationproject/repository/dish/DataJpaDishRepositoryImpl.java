package ru.sndolgov.graduationproject.repository.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sndolgov.graduationproject.model.Dish;
import ru.sndolgov.graduationproject.repository.restaurant.CrudRestaurantRepository;

import java.util.List;

/**
 * Created by Сергей on 07.02.2018.
 */
@Repository
public class DataJpaDishRepositoryImpl {

    @Autowired
    private CrudDishRepository crudDishRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Transactional
    public boolean delete(int id) {
        return crudDishRepository.delete(id) != 0;
    }

    public Dish get(int id, int restaurantId) {
        return crudDishRepository.findById(id).filter(dish -> dish.getRestaurant().getId() == restaurantId).orElse(null);
    }

    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.getId(), restaurantId) == null) {
            return null;
        }
        dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudDishRepository.save(dish);
    }

    public List<Dish> getAllByRestaurant(int restaurantId){
        return crudDishRepository.getAllByRestaurant(restaurantId);
    }
}
