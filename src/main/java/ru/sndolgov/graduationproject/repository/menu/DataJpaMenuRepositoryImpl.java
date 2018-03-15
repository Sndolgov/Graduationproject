package ru.sndolgov.graduationproject.repository.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.repository.restaurant.CrudRestaurantRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Сергей on 07.02.2018.
 */
@Repository
public class DataJpaMenuRepositoryImpl {

    @Autowired
    private CrudMenuRepository crudMenuRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Transactional
    public Menu save(Menu menu, int restaurantId) {
        if (!menu.isNew() && get(menu.getId(), restaurantId) == null) {
            return null;
        }
        menu.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMenuRepository.save(menu);
    }

    public Menu get(int id, int restaurantId) {
        return crudMenuRepository.findById(id).filter(menu -> menu.getRestaurant().getId() == restaurantId).orElse(null);
    }

    @Transactional
    public boolean delete(int id) {
        return crudMenuRepository.delete(id) != 0;
    }

    public List<Menu> getAll(int restaurantId) {
        return crudMenuRepository.getAll(restaurantId);
    }

    public Menu getWithRestaurantAndDishes(int id) {
        return crudMenuRepository.getWithRestaurantAndDishes(id);
    }

    public List<Menu> getAllByDate(Date date) {
        List<Menu> menus = crudMenuRepository.getAllByDate(date);
        menus.forEach(menu -> menu.setVoices(getWithVoices(menu.getId(), menu.getRestaurant().getId()).getVoices()));
        return menus;
    }

    public Menu getWithVoices(int id, int restaurantId) {
        return crudMenuRepository.getWithVoices(id, restaurantId);
    }

    public Menu getWithDishes(int id, int restaurantId) {
        return crudMenuRepository.getWithDishes(id, restaurantId);
    }

    public Menu getWithDishesVoices(int id, int restaurantId) {
        Menu withVoices = getWithVoices(id, restaurantId);
        Menu menu = getWithDishes(id, restaurantId);
        menu.setVoices(withVoices.getVoices());
        return menu;
    }
}
