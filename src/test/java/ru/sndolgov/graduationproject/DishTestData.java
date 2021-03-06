package ru.sndolgov.graduationproject;

import ru.sndolgov.graduationproject.model.Dish;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

import static ru.sndolgov.graduationproject.model.AbstractBaseEntity.START_SEQ;

/**
 * Created by Сергей on 12.02.2018.
 */
public class DishTestData {

    public static final int DISH1_ID = START_SEQ + 4;
    public static final int DISH2_ID = START_SEQ + 5;
    public static final int DISH3_ID = START_SEQ + 6;
    public static final int DISH4_ID = START_SEQ + 7;
    public static final int DISH5_ID = START_SEQ + 8;
    public static final int DISH6_ID = START_SEQ + 9;

    public static final Dish DISH1 = new Dish(DISH1_ID, "Soup", 200);
    public static final Dish DISH2 = new Dish(DISH2_ID, "Meat", 300);
    public static final Dish DISH3 = new Dish(DISH3_ID, "Juice", 100);
    public static final Dish DISH4 = new Dish(DISH4_ID, "Fish", 250);
    public static final Dish DISH5 = new Dish(DISH5_ID, "Eggs", 110);
    public static final Dish DISH6 = new Dish(DISH6_ID, "Pancakes", 150);


    public static Dish getCreated() {
        return new Dish(null, "NewDish", 500);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "UpdatedDish", 600);
    }

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }

}
