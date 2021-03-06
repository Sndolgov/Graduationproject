package ru.sndolgov.graduationproject.web.voting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.sndolgov.graduationproject.AuthorizedUser;
import ru.sndolgov.graduationproject.model.Voice;
import ru.sndolgov.graduationproject.service.menu.MenuService;
import ru.sndolgov.graduationproject.service.voice.VoiceService;
import ru.sndolgov.graduationproject.to.RestaurantTo;
import ru.sndolgov.graduationproject.util.DateUtil;
import ru.sndolgov.graduationproject.util.RestaurantUtil;
import ru.sndolgov.graduationproject.util.exception.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Сергей on 07.02.2018.
 */

@RestController
@RequestMapping(value = "/ajax/profile/voting")
public class VotingAjaxController {

    protected final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private MenuService menuService;

    @Autowired
    private VoiceService voiceService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RestaurantTo> getAllTodayMenus() {
        log.info("voting getAll");
        return menuService.getAllByDate(DateUtil.getDateToday()).stream()
                .map(RestaurantUtil::asTo)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteVoice(@PathVariable("id") int id) {
        voiceService.delete(id);
    }

    @PutMapping(value = "/{id}")
    public void vote(@PathVariable("id") int id) {
        log.info("voted for menu{}", id);
        voiceService.creat(id, AuthorizedUser.get().getId(), DateUtil.getDateToday());
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
