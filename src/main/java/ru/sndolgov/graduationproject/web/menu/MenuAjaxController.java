package ru.sndolgov.graduationproject.web.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.sndolgov.graduationproject.ChangeableRestaurant;
import ru.sndolgov.graduationproject.model.Dish;
import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.service.dish.DishService;
import ru.sndolgov.graduationproject.service.menu.MenuService;
import ru.sndolgov.graduationproject.service.restaurant.RestaurantService;
import ru.sndolgov.graduationproject.to.DishTo;
import ru.sndolgov.graduationproject.to.MenuTo;
import ru.sndolgov.graduationproject.to.RestaurantTo;
import ru.sndolgov.graduationproject.util.DishUtil;
import ru.sndolgov.graduationproject.util.MenuUtil;
import ru.sndolgov.graduationproject.util.RestaurantUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ru.sndolgov.graduationproject.util.ValidationUtil.assureIdConsistent;

/**
 * Created by Сергей on 07.02.2018.
 */

@RestController
@RequestMapping(value = "/ajax/admin/menus")
public class MenuAjaxController {

    protected final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private DishService dishService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantTo> getAll() {
        log.info("get menus of restautant {}", ChangeableRestaurant.id);
        List<Menu> menus = restaurantService.getWithAllFields(ChangeableRestaurant.id).getMenus();
        return menus.stream()
                .map(RestaurantUtil::asToActual)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MenuTo getById(@PathVariable("id") int id) {
        log.info("get menu {}", id);
        Menu menu = menuService.getWithRestaurantAndDishes(id, ChangeableRestaurant.id);
        return MenuUtil.asTo(menu);
    }

    @GetMapping(value = "/{id}/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DishTo> getDishesById(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        log.info("get dishes of menu {}", id);
        List<Dish> menuDishes = menuService.getWithDishes(id, restaurantId).getDishes();
        return restaurantService.getWithDishes(restaurantId).getDishes().stream()
                .filter(Dish::isEnabled)
                .map(dish -> DishUtil.asToIncluded(id, dish, menuDishes))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "dishes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DishTo> getDishes(@PathVariable("id") int id) {
        log.info("get dishes of restaurant {}", id);
        return restaurantService.getWithDishes(id).getDishes().stream()
                .map(dish -> DishUtil.asTo(id, dish))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "dishes/{id}/{parentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DishTo getDish(@PathVariable("id") int id, @PathVariable("parentId") int parentId) {
        log.info("get dish {}", id);
        return DishUtil.asTo(parentId, dishService.get(id, parentId));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("menu delete {}", id);
        menuService.delete(id);
    }

    @DeleteMapping(value = "dishes/{id}")
    public void deleteDish(@PathVariable("id") int id) {
        log.info("dish delete {}", id);
        dishService.delete(id);
    }

    @PostMapping(value = "/{dishId}/{menuId}")
    public void enable(@PathVariable("dishId") int dishId, @PathVariable("menuId") int menuId, @RequestParam("enabled") boolean enabled) {
        if (enabled) {
            menuService.addDish(menuId, dishId, ChangeableRestaurant.id);
        } else {
            menuService.deletDish(menuId, dishId, ChangeableRestaurant.id);
        }
    }

    @PostMapping
    public void createOrUpdate(@Valid MenuTo menuTo) {
        if (menuTo.isNew()) {
            Menu menu = MenuUtil.createNewFromTo(menuTo);
            log.info("menu create {}", menu);
            menuService.create(menu, ChangeableRestaurant.id);
        } else {
            assureIdConsistent(menuTo, menuTo.getId());
            log.info("menu update {}", menuTo);
            menuService.update(menuTo);
        }
    }

    @PostMapping (value = "/dishes")
    public void createOrUpdateDish(@Valid DishTo dishTo) {
        if (dishTo.isNew()) {
            Dish dish = DishUtil.createNewFromTo(dishTo);
            log.info("dish create {}", dish);
            dishService.create(dish, ChangeableRestaurant.id);
        } else {
            assureIdConsistent(dishTo, dishTo.getId());
            log.info("dish update {}", dishTo);
            dishService.update(dishTo);
        }
    }

}
