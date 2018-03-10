package ru.sndolgov.graduationproject.repository.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.model.Restaurant;
import ru.sndolgov.graduationproject.repository.menu.CrudMenuRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DataJpaRestaurantRepositoryImpl{
    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");


    @Autowired
    CrudRestaurantRepository crudRepository;

    @Autowired
    CrudMenuRepository crudMenuRepository;

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return crudRepository.save(restaurant);
    }

    @Transactional
    public boolean delete(int id) {
        return crudRepository.delete(id)!=0;
    }

    public Restaurant get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    public Restaurant getByName(String name) {
        return crudRepository.getByName(name);
    }

    public List<Restaurant> getAll() {
        return crudRepository.findAll(SORT_NAME);
    }

    public Restaurant getWithMenus(int id){
        Restaurant restaurant = crudRepository.getWithMenu(id);
        List<Menu> menus = restaurant.getMenus();
        List<Menu> menusWithDishes = new ArrayList<>();
        for (Menu menu: menus){
            menusWithDishes.add(crudMenuRepository.getWithDishes(menu.getId()));
        }
        restaurant.setMenus(menusWithDishes);
        return restaurant;
    }
}
