package ru.sndolgov.graduationproject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import ru.sndolgov.graduationproject.AuthorizedUser;
import ru.sndolgov.graduationproject.ChangeableRestaurant;
import ru.sndolgov.graduationproject.model.Restaurant;
import ru.sndolgov.graduationproject.service.restaurant.RestaurantService;
import ru.sndolgov.graduationproject.to.RestaurantTo;
import ru.sndolgov.graduationproject.to.UserTo;
import ru.sndolgov.graduationproject.util.UserUtil;
import ru.sndolgov.graduationproject.web.user.AbstractUserController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static ru.sndolgov.graduationproject.web.ExceptionInfoHandler.EXCEPTION_DUPLICATE_EMAIL;

@Controller
public class RootController extends AbstractUserController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/")
    public String root() {
        return "redirect:voting";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/voting")
    public String voting(ModelMap model) {
        return "voting";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/restaurant/{id}")
    public String restaurant(ModelMap model, @PathVariable("id") int id) {
        Restaurant restaurant = restaurantService.get(id);
        ChangeableRestaurant.setId(id);
        model.addAttribute("restaurant", restaurant);
        return "restaurant";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/restaurants")
    public String restaurants(ModelMap model) {
        return "restaurants";
    }

    @GetMapping("/profile")
    public String profile(ModelMap model, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        model.addAttribute("userTo", authorizedUser.getUserTo());
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid UserTo userTo, BindingResult result, SessionStatus status, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        if (result.hasErrors()) {
            return "profile";
        }
        try {
            super.update(userTo, authorizedUser.getId());
            authorizedUser.update(userTo);
            status.setComplete();
            return "redirect:voting";
        } catch (DataIntegrityViolationException ex) {
            result.rejectValue("email", EXCEPTION_DUPLICATE_EMAIL);
            return "profile";
        }
    }

    @GetMapping("/register")
    public String register(ModelMap model) {
        model.addAttribute("userTo", new UserTo());
        model.addAttribute("register", true);
        return "profile";
    }

    @PostMapping("/register")
    public String saveRegister(@Valid UserTo userTo, BindingResult result, SessionStatus status, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("register", true);
            return "profile";
        }
        try {
            super.create(UserUtil.createNewFromTo(userTo));
            status.setComplete();
            return "redirect:login?message=app.registered&username=" + userTo.getEmail();
        } catch (DataIntegrityViolationException ex) {
            result.rejectValue("email", EXCEPTION_DUPLICATE_EMAIL);
            model.addAttribute("register", true);
            return "profile";
        }
    }
}