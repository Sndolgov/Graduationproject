package ru.sndolgov.graduationproject;

import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.util.DateUtil;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.sndolgov.graduationproject.model.AbstractBaseEntity.START_SEQ;

/**
 * Created by Сергей on 07.02.2018.
 */
public class MenuTestData {
    public static final int MENU1_ID = START_SEQ + 15;
    public static final int MENU2_ID = START_SEQ + 16;
    public static final int MENU3_ID = START_SEQ + 17;
    public static final int MENU4_ID = START_SEQ + 18;
    public static final int MENU5_ID = START_SEQ + 19;
    public static final int MENU6_ID = START_SEQ + 20;

    public static final Menu MENU1 = new Menu(MENU1_ID, "Menu of First restaurant", DateUtil.of(2017, 12, 26));
    public static final Menu MENU2 = new Menu(MENU2_ID, "Menu of First restaurant", DateUtil.of(2017, 12, 27));
    public static final Menu MENU3 = new Menu(MENU3_ID, "Menu of First restaurant", DateUtil.getDateToday());
    public static final Menu MENU4 = new Menu(MENU4_ID, "Menu of Second restaurant", DateUtil.of(2017, 12, 26));
    public static final Menu MENU5 = new Menu(MENU5_ID, "Menu of Second restaurant", DateUtil.of(2017, 12, 27));
    public static final Menu MENU6 = new Menu(MENU6_ID, "Menu of Second restaurant", DateUtil.getDateToday());

    public static Menu getCreatedMenu() {
        return new Menu(null, "NewMenu", DateUtil.of(2018, 2, 12));
    }

    public static Menu getUpdatedMenu() {
        return new Menu(MENU1_ID, "UpdatedMenu", DateUtil.of(2018, 2, 12));
    }

    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant", "date", "dishes", "voices");
    }

    public static void assertMatch(Iterable<Menu> actual, Menu... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        //  assertThat(actual).isEqualTo(expected);
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "date", "dishes", "voices").isEqualTo(expected);
    }
}
