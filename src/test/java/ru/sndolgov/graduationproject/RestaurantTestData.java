package ru.sndolgov.graduationproject;


import ru.sndolgov.graduationproject.model.Restaurant;

import java.util.Arrays;

import static ru.sndolgov.graduationproject.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;


public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_SEQ + 2;
    public static final int RESTAURANT2_ID = START_SEQ + 3;


    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Restaurant1", true);
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT2_ID, "Restaurant2", true);

    public static Restaurant getCreatedRestaurant(){
        return new Restaurant(null, "NewRestaurant", true);
    }

    public static Restaurant getUpdatedRestaurant(){
        return new Restaurant(RESTAURANT1_ID, "UpdatedRestaurant", true);
    }


    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected,"menus", "dishes");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
