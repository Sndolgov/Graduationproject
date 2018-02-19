package ru.sndolgov.graduationproject;

import ru.sndolgov.graduationproject.model.Voice;

import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;

import static ru.sndolgov.graduationproject.MenuTestData.MENU1;
import static ru.sndolgov.graduationproject.RestaurantTestData.RESTAURANT1;
import static ru.sndolgov.graduationproject.UserTestData.ADMIN;
import static ru.sndolgov.graduationproject.UserTestData.USER;
import static ru.sndolgov.graduationproject.model.AbstractBaseEntity.START_SEQ;

/**
 * Created by Сергей on 13.02.2018.
 */
public class VoiceTestData {
    public static final int VOICE1_ID = START_SEQ+21;
    public static final int VOICE2_ID = START_SEQ +22;

    public static final Voice VOICE1 = new Voice(VOICE1_ID, MENU1, RESTAURANT1, USER);
    public static final Voice VOICE2 = new Voice(VOICE2_ID, MENU1, RESTAURANT1, ADMIN);

    public static void assertMatch(Voice actual, Voice expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant", "user");
    }

    public static void assertMatch(Iterable<Voice> actual, Voice... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Voice> actual, Iterable<Voice> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "user").isEqualTo(expected);
    }
}
