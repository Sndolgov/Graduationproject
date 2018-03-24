package ru.sndolgov.graduationproject.service.restaurant;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.sndolgov.graduationproject.DishTestData;
import ru.sndolgov.graduationproject.RestaurantTestData;
import ru.sndolgov.graduationproject.model.Restaurant;
import ru.sndolgov.graduationproject.service.AbstractServiceTest;
import ru.sndolgov.graduationproject.util.exception.NotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.List;
import static ru.sndolgov.graduationproject.DishTestData.*;
import static ru.sndolgov.graduationproject.RestaurantTestData.*;
import static ru.sndolgov.graduationproject.UserTestData.USER_ID;

public class RestaurantServiceTest extends AbstractServiceTest {
    @Autowired
    protected RestaurantService service;

    //TODO cache

/*    @Autowired
    private JpaUtil jpaUtil;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("restaurants").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }*/

    @Test
    public void getAll() throws Exception {
        List<Restaurant> all = service.getAll();
        assertMatch(all, RESTAURANT1, RESTAURANT2);
    }

    @Test
    public void create() throws Exception {
        Restaurant newRestaurant = RestaurantTestData.getCreated();
        Restaurant created = service.create(newRestaurant);
        newRestaurant.setId(created.getId());
        assertMatch(service.getAll(), newRestaurant, RESTAURANT1, RESTAURANT2);
    }

    @Test
    public void update() throws Exception {
        Restaurant updated = RestaurantTestData.getUpdated();
        service.update(updated);
        assertMatch(service.get(RESTAURANT1_ID), updated);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateNameCreate() throws Exception {
        service.create(new Restaurant(null, "Restaurant1", false));
    }

    @Test
    public void delete() throws Exception {
        service.delete(RESTAURANT1_ID);
        assertMatch(service.getAll(), RESTAURANT2);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(USER_ID);
    }

    @Test
    public void get() throws Exception {
        Restaurant restaurant = service.get(RESTAURANT1_ID);
        assertMatch(restaurant, RESTAURANT1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(USER_ID);
    }

   /* @Test
    public void getByEmail() throws Exception {
        Restaurant restaurant = service.getByName("Restaurant1");
        assertMatch(restaurant, RESTAURANT1);
    }*/

    /*@Test(expected = NotFoundException.class)
    public void getByNameNotFound() throws Exception {
        Restaurant restaurant = service.getByName("Restaurant3");
    }*/

    /*@Test
    public void getWithMenus() throws Exception {
        Restaurant restaurant = service.getWithMenus(RESTAURANT1_ID);
        MenuTestData.assertMatch(restaurant.getMenus(), MENU3, MENU2, MENU1);
    }*/

    @Test
    public void getWithDishes() throws Exception {
        Restaurant restaurant = service.getWithDishes(RESTAURANT1_ID);
        DishTestData.assertMatch(restaurant.getDishes(), DISH1, DISH2, DISH3, DISH4, DISH5, DISH6);
    }


    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> service.create(new Restaurant(null, "  ", false)), ConstraintViolationException.class);
    }

}
