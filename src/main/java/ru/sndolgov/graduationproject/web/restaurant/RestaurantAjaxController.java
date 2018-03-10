package ru.sndolgov.graduationproject.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.sndolgov.graduationproject.AuthorizedUser;
import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.model.Restaurant;
import ru.sndolgov.graduationproject.model.Voice;
import ru.sndolgov.graduationproject.service.menu.MenuService;
import ru.sndolgov.graduationproject.service.restaurant.RestaurantService;
import ru.sndolgov.graduationproject.service.voice.VoiceService;
import ru.sndolgov.graduationproject.to.RestaurantTo;
import ru.sndolgov.graduationproject.util.DateUtil;
import ru.sndolgov.graduationproject.util.RestaurantUtil;
import ru.sndolgov.graduationproject.util.exception.NotFoundException;

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

    @Autowired
    private VoiceService voiceService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantTo> getAll() {
        log.info("restaurant getAll");
        List<Menu> menus = menuService.getAllTodayWithRestaraunt(DateUtil.getDateToday());
        return menus.stream()
                .map(RestaurantUtil::asTo)
                .collect(Collectors.toList());
    }

    @DeleteMapping("admin/{id}")
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

    @PutMapping(value = "/{id}")
    public void vote(@PathVariable("id") int id) {
        log.info("voted for menu{}", id);
        voiceService.creat(id, AuthorizedUser.get().getId(), DateUtil.getDateToday());
    }

    @DeleteMapping("/deletevoice/{id}")
    public void deleteVoice(@PathVariable("id") int id) {
        voiceService.delete(id);
    }

    @GetMapping("/getvoice")
    public Integer getVoice() {
        Integer id = 0;
        try {
            Voice voice = voiceService.getByDate(DateUtil.getDateToday(), AuthorizedUser.get().getId());
            id = voice.getId();
        } catch (NotFoundException e) {
            System.out.println(e);
        }
        return id;
    }
}
