package ru.sndolgov.graduationproject.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.model.Restaurant;
import ru.sndolgov.graduationproject.service.menu.MenuService;
import ru.sndolgov.graduationproject.service.restaurant.RestaurantService;
import ru.sndolgov.graduationproject.to.RestaurantTo;
import ru.sndolgov.graduationproject.util.DateUtil;
import ru.sndolgov.graduationproject.util.RestaurantUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ru.sndolgov.graduationproject.util.ValidationUtil.assureIdConsistent;

/**
 * Created by Сергей on 07.02.2018.
 */

@RestController
@RequestMapping(value = "/ajax/profile/restaurants")
public class RestaurantAjaxController {

    protected final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantTo> getAll() {
        log.info("restaurant getAll");
        List<Menu> menus = menuService.getAllTodayWithRestaraunt(DateUtil.getDateToday());
        return menus.stream()
                .map(RestaurantUtil::asTo)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        log.info("restaurant get {}", id);
        return restaurantService.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("restaurant delete {}", id);
        restaurantService.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@Valid Restaurant restaurant) {
        if (restaurant.isNew()) {
            log.info("restaurant create {}", restaurant);
            restaurantService.create(restaurant);
        } else {
            log.info("restaurant update {}", restaurant);
            assureIdConsistent(restaurant, restaurant.getId());
            restaurantService.update(restaurant);
        }
    }
}
