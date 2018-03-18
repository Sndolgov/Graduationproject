package ru.sndolgov.graduationproject.web.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.sndolgov.graduationproject.ChangeableRestaurant;
import ru.sndolgov.graduationproject.model.Dish;
import ru.sndolgov.graduationproject.model.Menu;
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


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantTo> getAll() {
        log.info("get menus of restautant {}", ChangeableRestaurant.id);
        List<Menu> menus = restaurantService.getWithMenusDishesVoices(ChangeableRestaurant.id).getMenus();
        return menus.stream()
                .map(RestaurantUtil::asTo)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("menu delete {}", id);
        menuService.delete(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MenuTo getById(@PathVariable("id") int id) {
        log.info("get menu", id);
        Menu menu = menuService.getWithRestaurantAndDishes(id, ChangeableRestaurant.id);
        return MenuUtil.asTo(menu);

    }

    @GetMapping(value = "dishes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DishTo> getDishesById(@PathVariable("id") int id) {
        log.info("get diashes of menu", id);
        List<Dish> menuDishes = menuService.getWithDishes(id, ChangeableRestaurant.id).getDishes();
        return restaurantService.getWithDishes(ChangeableRestaurant.id).getDishes().stream()
                .map(dish -> DishUtil.asTo(id, dish, menuDishes))
                .collect(Collectors.toList());
    }

    @PostMapping (value = "/{dishId}/{menuId}")
    public void enable (@PathVariable("dishId") int dishId, @PathVariable("menuId") int menuId, @RequestParam("enabled") boolean enabled){
        if (enabled){
            menuService.addDish(menuId, dishId, ChangeableRestaurant.id);
        }
        else {
            menuService.deletDish(menuId, dishId, ChangeableRestaurant.id);
        }
    }

    @PostMapping
    public void createOrUpdate(MenuTo menuTo) {
        if (menuTo.isNew()) {
            Menu menu = MenuUtil.createNewFromTo(menuTo);
            log.info("menu create {}", menu);
            menuService.creat(menu, menuTo.getRestaurantId());
        } else {
            assureIdConsistent(menuTo, menuTo.getId());
            log.info("menu update {}", menuTo);
            menuService.update(menuTo);
        }
    }

}
