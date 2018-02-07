package ru.sndolgov.graduationproject.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sndolgov.graduationproject.model.Restaurant;
import ru.sndolgov.graduationproject.service.restaurant.RestaurantService;
import ru.sndolgov.graduationproject.to.RestaurantTo;
import ru.sndolgov.graduationproject.util.RestaurantUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static ru.sndolgov.graduationproject.util.RestaurantUtil.asTo;

/**
 * Created by Сергей on 07.02.2018.
 */

@RestController
@RequestMapping(value = "/ajax/profile/restaurants")
public class RestaurantAjaxController {

    @Autowired
    private RestaurantService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantTo> getAll() {
        return service.getAll().stream()
                .map(RestaurantUtil::asTo)
                .collect(Collectors.toList());

    }
}
