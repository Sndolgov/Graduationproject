package ru.sndolgov.graduationproject.service.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.repository.menu.DataJpaMenuRepositoryImpl;
import ru.sndolgov.graduationproject.util.exception.NotFoundException;

import java.util.Date;
import java.util.List;

import static ru.sndolgov.graduationproject.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Сергей on 07.02.2018.
 */
@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    DataJpaMenuRepositoryImpl repository;

    @Override
    public Menu creat(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        return repository.save(menu, restaurantId);
    }

    @Override
    public Menu update(Menu menu, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(repository.save(menu, restaurantId), menu.getId());
    }

    @Override
    public Menu get(int id, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);    }

    @Override
    public void delete(int id, int restaurantId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    @Override
    public List<Menu> getAll(int restaurantId) {
        return repository.getAll(restaurantId);    }

    @Override
    public Menu getWithRestaurant(int id) {
        return checkNotFoundWithId(repository.getWithRestaurant(id), id);    }

    @Override
    public Menu getWithDishes(int id) {
        return checkNotFoundWithId(repository.getWithDishes(id), id);    }

    @Override
    public List<Menu> getAllTodayWithRestaraunt(Date date) {
        return repository.getAllTodayWithRestaraunt(date);
    }

    @Override
    public Menu getWithVoices(int id, int restaurantId) {
        return repository.getWithVoices(id, restaurantId);
    }
}
