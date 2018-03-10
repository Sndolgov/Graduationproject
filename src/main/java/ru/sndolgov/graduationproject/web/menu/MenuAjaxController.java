package ru.sndolgov.graduationproject.web.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
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

import javax.servlet.http.HttpServletRequest;
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
    private VoiceService voiceService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantTo> getAll(@PathVariable("id") int id) {
        log.info("get menus of restautant {}", id);
        return restaurantService.getWithMenus(id).getMenus().stream()
                .map(menu -> RestaurantUtil.asTo(menuService.getWithRestaurantAndDishes(menu.getId())))
                .collect(Collectors.toList());
    }

    @DeleteMapping("admin/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("restaurant delete {}", id);
        menuService.delete(id);
    }


}
