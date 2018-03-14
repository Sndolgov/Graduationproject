package ru.sndolgov.graduationproject.web.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.sndolgov.graduationproject.ChangeableRestaurant;
import ru.sndolgov.graduationproject.model.Dish;
import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.service.menu.MenuService;
import ru.sndolgov.graduationproject.service.restaurant.RestaurantService;
import ru.sndolgov.graduationproject.to.MenuTo;
import ru.sndolgov.graduationproject.to.RestaurantTo;
import ru.sndolgov.graduationproject.util.MenuUtil;
import ru.sndolgov.graduationproject.util.RestaurantUtil;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        Menu menu = menuService.getWithRestaurantAndDishes(id);
        if (menu!=null && menu.getRestaurant().getId() == ChangeableRestaurant.id) {
            return MenuUtil.asTo(menu);
        }
        return null;
    }

    @GetMapping(value = "dishes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getByDishesId(@PathVariable("id") int id) {
        log.info("get diashes of menu", id);
        Menu menu = menuService.getWithDishes(id, ChangeableRestaurant.id);
        if (menu!=null && menu.getRestaurant().getId() == ChangeableRestaurant.id) {
            return menu.getDishes();
        }
        return null;
    }

    @PostMapping
    public void createOrUpdate(MenuTo menuTo)  {
        System.out.println();
    }
}
