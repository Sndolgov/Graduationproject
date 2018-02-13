package ru.sndolgov.graduationproject.service.menu;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.sndolgov.graduationproject.DishTestData;
import ru.sndolgov.graduationproject.MenuTestData;
import ru.sndolgov.graduationproject.RestaurantTestData;
import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.service.AbstractServiceTest;
import ru.sndolgov.graduationproject.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import static ru.sndolgov.graduationproject.DishTestData.*;
import static ru.sndolgov.graduationproject.MenuTestData.*;
import static ru.sndolgov.graduationproject.MenuTestData.assertMatch;
import static ru.sndolgov.graduationproject.RestaurantTestData.RESTAURANT1;
import static ru.sndolgov.graduationproject.RestaurantTestData.RESTAURANT1_ID;
import static ru.sndolgov.graduationproject.RestaurantTestData.RESTAURANT2_ID;
import static ru.sndolgov.graduationproject.UserTestData.USER_ID;

/**
 * Created by Сергей on 07.02.2018.
 */
public class MenuServiceImplTest extends AbstractServiceTest {
    @Autowired
    MenuService service;

    @Test
    public void creat() throws Exception {
        Menu newMenu = MenuTestData.getCreated();
        Menu created = service.creat(newMenu, RESTAURANT1_ID);
        newMenu.setId(created.getId());
        assertMatch(service.getAll(RESTAURANT1_ID), newMenu, MENU3, MENU2, MENU1);
    }

    @Test(expected = DataAccessException.class)
    public void creatDuplicateDate() throws Exception {
        Menu newMenu = MenuTestData.getCreated();
        newMenu.setDate(MENU1.getDate());
        service.creat(newMenu, RESTAURANT1_ID);
    }

    @Test
    public void update() throws Exception {
        Menu updated = MenuTestData.getUpdated();
        service.update(updated, RESTAURANT1_ID);
        assertMatch(service.getAll(RESTAURANT1_ID), updated, MENU3, MENU2);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        Menu updated = MenuTestData.getUpdated();
        service.update(updated, RESTAURANT2_ID);
    }

    @Test
    public void get() throws Exception {
        Menu menu = service.get(MENU1_ID, RESTAURANT1_ID);
        assertMatch(menu, MENU1);
        DishTestData.assertMatch(menu.getDishes(), DISH1, DISH2, DISH3, DISH4);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(MENU1_ID, RESTAURANT2_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(MENU1_ID, RESTAURANT2_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MENU1_ID, RESTAURANT1_ID);
        assertMatch(service.getAll(RESTAURANT1_ID), MENU3, MENU2);
    }

    @Test
    public void getAll() throws Exception {
        List<Menu> menus = service.getAll(RESTAURANT1_ID);
        assertMatch(menus, MENU3, MENU2, MENU1);
    }

    @Test
    public void getAllEmpty() throws Exception {
        List<Menu> menus = service.getAll(USER_ID);
        assertMatch(menus, new ArrayList<>());
    }

    @Test
    public void getWithRestaurant() throws Exception {
        Menu menu = service.getWithRestaurant(MENU1_ID, RESTAURANT1_ID);
        System.out.println(menu.getRestaurant());
        RestaurantTestData.assertMatch(menu.getRestaurant(), RESTAURANT1);
    }
}