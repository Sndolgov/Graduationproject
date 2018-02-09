package ru.sndolgov.graduationproject.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.sndolgov.graduationproject.model.Restaurant;
import ru.sndolgov.graduationproject.service.restaurant.RestaurantService;
import ru.sndolgov.graduationproject.to.RestaurantTo;
import ru.sndolgov.graduationproject.util.RestaurantUtil;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static ru.sndolgov.graduationproject.util.RestaurantUtil.asTo;
import static ru.sndolgov.graduationproject.util.ValidationUtil.assureIdConsistent;

/**
 * Created by Сергей on 07.02.2018.
 */

@RestController
@RequestMapping(value = "/ajax/profile/restaurants")
public class RestaurantAjaxController {

    protected final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private RestaurantService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantTo> getAll() {
        log.info("restaurant getAll");

        return service.getAll().stream()
                .map(RestaurantUtil::asTo)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        log.info("restaurant get {}", id);
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("restaurant delete {}", id);
        service.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@Valid Restaurant restaurant) {
        if (restaurant.isNew()) {
            log.info("restaurant create {}", restaurant);
            service.create(restaurant);
        } else {
            log.info("restaurant update {}", restaurant);
            assureIdConsistent(restaurant, restaurant.getId());
            service.update(restaurant);
        }
    }
}
