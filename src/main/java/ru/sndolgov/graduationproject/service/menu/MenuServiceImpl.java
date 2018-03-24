package ru.sndolgov.graduationproject.service.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.sndolgov.graduationproject.model.Dish;
import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.repository.dish.DataJpaDishRepositoryImpl;
import ru.sndolgov.graduationproject.repository.menu.DataJpaMenuRepositoryImpl;
import ru.sndolgov.graduationproject.to.MenuTo;
import ru.sndolgov.graduationproject.util.MenuUtil;
import ru.sndolgov.graduationproject.util.exception.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static ru.sndolgov.graduationproject.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Сергей on 07.02.2018.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    DataJpaMenuRepositoryImpl repository;

    @Autowired
    DataJpaDishRepositoryImpl dishRepository;

    @Override
    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        return repository.save(menu, restaurantId);
    }

    @Override
    public Menu update(Menu menu, int restaurantId) throws NotFoundException {
        return repository.save(menu, restaurantId);
    }

    @Override
    public Menu update(MenuTo menuTo) {
        Menu menu = MenuUtil.updateFromTo(get(menuTo.getId(), menuTo.getRestaurantId()), menuTo);
        return update(menu, menuTo.getRestaurantId());
    }

    @Override
    public Menu get(int id, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public List<Menu> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    @Override
    public Menu getWithRestaurant(int id) throws NotFoundException{
        return checkNotFoundWithId(repository.getWithRestaurant(id), id);
    }

    @Override
    public Menu getWithRestaurantAndDishes(int id, int restaurantId) throws NotFoundException{
        return checkNotFoundWithId(repository.getWithRestaurantAndDishes(id, restaurantId), id);
    }

    @Override
    public Menu getWithDishes(int id, int restaurantId) throws NotFoundException{
        return checkNotFoundWithId(repository.getWithDishes(id, restaurantId), id);
    }

    @Override
    public List<Menu> getAllByDate(Date date) {
        return repository.getAllByDate(date);
    }

    @Override
    public Menu getWithVoices(int id, int restaurantId) throws NotFoundException{
        return checkNotFoundWithId(repository.getWithVoices(id, restaurantId), id);
    }

    @Override
    public Menu getWithDishesVoices(int id, int restaurantId) throws NotFoundException{
        return checkNotFoundWithId(repository.getWithDishes(id, restaurantId), id);
    }

    @Override
    public void addDish(int menuId, int dishId, int restaurantId) {
        Menu menu = getWithDishes(menuId, restaurantId);
        List<Dish> dishes = menu.getDishes();
        Dish dish = dishRepository.get(dishId, restaurantId);
        dishes.add(dish);
        menu.setDishes(dishes);
        update(menu, restaurantId);
    }

    @Override
    public void deletDish(int menuId, int dishId, int restaurantId) {
        Menu menu = getWithDishes(menuId, restaurantId);
        List<Dish> dishes = menu.getDishes();
        menu.setDishes(dishes.stream()
                .filter(dish1 -> dish1.getId()!=dishId)
                .collect(Collectors.toList()));
        update(menu, restaurantId);
    }
}
