package ru.sndolgov.graduationproject.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.sndolgov.graduationproject.model.User;
import ru.sndolgov.graduationproject.service.user.UserService;
import ru.sndolgov.graduationproject.to.UserTo;
import ru.sndolgov.graduationproject.util.UserUtil;


import javax.validation.Valid;
import java.util.List;

import static ru.sndolgov.graduationproject.util.ValidationUtil.assureIdConsistent;
import static ru.sndolgov.graduationproject.util.ValidationUtil.checkNew;

@RestController
@RequestMapping("/ajax/admin/users")
public class AdminAjaxController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@Valid UserTo userTo) {
        if (userTo.isNew()) {
            User user = UserUtil.createNewFromTo(userTo);
            log.info("create {}", user);
            checkNew(user);
            service.create(user);
        } else {
            log.info("update {} with id={}", userTo, userTo.getId());
            assureIdConsistent(userTo, userTo.getId());
            service.update(userTo);
        }
    }

    @PostMapping(value = "/{id}")
    public void enable(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        log.info((enabled ? "enable " : "disable ") + id);
        service.enable(id, enabled);
    }
}

