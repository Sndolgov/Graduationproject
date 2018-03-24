package ru.sndolgov.graduationproject.service.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.model.Restaurant;
import ru.sndolgov.graduationproject.repository.menu.DataJpaMenuRepositoryImpl;
import ru.sndolgov.graduationproject.repository.restaurant.DataJpaRestaurantRepositoryImpl;
import ru.sndolgov.graduationproject.util.exception.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import static ru.sndolgov.graduationproject.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantServiceImpl implements RestaurantService{


    @Autowired
    DataJpaRestaurantRepositoryImpl restaurantRepository;

    @Autowired
    DataJpaMenuRepositoryImpl menuRepository;

   // @CacheEvict(value = "restaurants", allEntries = true)

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "user must not be null");
        return restaurantRepository.save(restaurant);
    }

   // @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(restaurantRepository.get(id), id);
    }


   // @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }


    @Override
    public void enable(int id, boolean enable) {
        Restaurant restaurant = get(id);
        restaurant.setEnabled(enable);
        restaurantRepository.save(restaurant);
    }

    // @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.getId());
    }

    @Override
    public Restaurant getWithDishes(int restaurantId) {
        return checkNotFoundWithId(restaurantRepository.getWithDishes(restaurantId), restaurantId);
    }

    @Override
    public Restaurant getWithAllFields(int id) {
        Restaurant restaurant = restaurantRepository.getWithMenus(id);

        List<Menu> menusWithDishes =restaurant.getMenus().stream()
                .map(menu -> menuRepository.getWithDishesVoices(menu.getId(), id))
                .collect(Collectors.toList());

        menusWithDishes.forEach(menu -> menu.setRestaurant(restaurant));

        restaurant.setMenus(menusWithDishes);
        return restaurant;
    }

}
