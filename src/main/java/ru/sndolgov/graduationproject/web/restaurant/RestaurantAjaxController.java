package ru.sndolgov.graduationproject.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.sndolgov.graduationproject.model.Restaurant;
import ru.sndolgov.graduationproject.service.restaurant.RestaurantService;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ru.sndolgov.graduationproject.util.ValidationUtil.assureIdConsistent;

/**
 * Created by Сергей on 07.02.2018.
 */

@RestController
@RequestMapping(value = "/ajax/admin/restaurants")
public class RestaurantAjaxController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        log.info("restaurants getAll");
        return  restaurantService.getAll();
    }

    @PostMapping(value = "/{id}")
    public void enable(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        log.info((enabled ? "enable " : "disable ") + id);
        restaurantService.enable(id, enabled);
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

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        return restaurantService.get(id);
    }
}
