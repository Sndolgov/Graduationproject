package ru.sndolgov.graduationproject.service.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.sndolgov.graduationproject.model.Dish;
import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.repository.dish.DataJpaDishRepositoryImpl;
import ru.sndolgov.graduationproject.to.DishTo;
import ru.sndolgov.graduationproject.util.DishUtil;

import java.util.List;

import static ru.sndolgov.graduationproject.util.UserUtil.prepareToSave;
import static ru.sndolgov.graduationproject.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Сергей on 14.03.2018.
 */

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    DataJpaDishRepositoryImpl repository;

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    @Override
    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish, restaurantId);
    }

    @Override
    public Dish update(Dish dish, int restaurantId) {
        return checkNotFoundWithId(repository.save(dish, restaurantId), dish.getId());
    }

    @Override
    public Dish update(DishTo menuTo) {
        Dish dish = DishUtil.updateFromTo(get(menuTo.getId(), menuTo.getParentId()), menuTo);
        return update(dish, menuTo.getParentId());
    }

    @Override
    public List<Dish> getAllByRestaurant(int restaurantId) {
        return repository.getAllByRestaurant(restaurantId);
    }

}
