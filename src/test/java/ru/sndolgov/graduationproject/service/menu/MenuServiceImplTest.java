package ru.sndolgov.graduationproject.service.menu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import ru.sndolgov.graduationproject.DishTestData;
import ru.sndolgov.graduationproject.MenuTestData;
import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.repository.JpaUtil;
import ru.sndolgov.graduationproject.service.AbstractServiceTest;
import ru.sndolgov.graduationproject.util.DateUtil;
import ru.sndolgov.graduationproject.util.exception.NotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import static ru.sndolgov.graduationproject.DishTestData.*;
import static ru.sndolgov.graduationproject.MenuTestData.*;
import static ru.sndolgov.graduationproject.MenuTestData.assertMatch;
import static ru.sndolgov.graduationproject.RestaurantTestData.*;
import static ru.sndolgov.graduationproject.UserTestData.USER_ID;

/**
 * Created by Сергей on 07.02.2018.
 */
public class MenuServiceImplTest extends AbstractServiceTest {
    @Autowired
    MenuService service;

    @Autowired
    private JpaUtil jpaUtil;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("menus").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    public void creat() throws Exception {
        Menu newMenu = getCreatedMenu();
        Menu created = service.create(newMenu, RESTAURANT1_ID);
        newMenu.setId(created.getId());
        assertMatch(service.getAll(RESTAURANT1_ID), MENU3, newMenu, MENU2, MENU1);
    }

    @Test(expected = DataAccessException.class)
    public void creatDuplicateDate() throws Exception {
        Menu newMenu = getCreatedMenu();
        newMenu.setDate(MENU1.getDate());
        service.create(newMenu, RESTAURANT1_ID);
    }

    @Test
    public void update() throws Exception {
        Menu updated = MenuTestData.getUpdatedMenu();
        service.update(updated, RESTAURANT1_ID);
        assertMatch(service.getAll(RESTAURANT1_ID), MENU3, updated, MENU2);
    }

    @Test
    public void updateDishes() throws Exception {
        Menu menu = service.getWithDishes(MENU1_ID, RESTAURANT1_ID);
        service.addDish(menu.getId(), DISH5.getId(), RESTAURANT1_ID);
        Menu updated = service.getWithDishes(MENU1_ID, RESTAURANT1_ID);
        DishTestData.assertMatch(updated.getDishes(), DISH1, DISH2, DISH3, DISH4, DISH5);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        Menu updated = getUpdatedMenu();
        service.update(updated, RESTAURANT2_ID);
    }

    @Test
    public void get() throws Exception {
        Menu menu = service.get(MENU1_ID, RESTAURANT1_ID);
        assertMatch(menu, MENU1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(MENU1_ID, RESTAURANT2_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(RESTAURANT1_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MENU1_ID);
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
    public void getAllByDate() throws Exception {
        List<Menu> menus = service.getAllByDate(DateUtil.getDateToday());
        assertMatch(menus, MENU3, MENU6);
    }

    @Test
    public void getWithDishes() throws Exception {
        Menu menu = service.getWithDishes(MENU1_ID, RESTAURANT1_ID);
        DishTestData.assertMatch(menu.getDishes(), DISH1, DISH2, DISH3, DISH4);
    }


    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> service.create(new Menu(null, " ", DateUtil.of(2018, 2, 12)), RESTAURANT1_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Menu(null, "NewMenu", null), RESTAURANT1_ID), ConstraintViolationException.class);

    }
}