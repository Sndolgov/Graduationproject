package ru.sndolgov.graduationproject.util;

import ru.sndolgov.graduationproject.model.Menu;
import ru.sndolgov.graduationproject.model.Restaurant;
import ru.sndolgov.graduationproject.to.MenuTo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class MenuUtil {

    public static MenuTo asTo (Menu menu){
       return new MenuTo(menu.getId(), menu.getRestaurant().getId(), menu.getDescription(), DateUtil.toLocalDate(menu.getDate()));
    }

    public static Menu createNewFromTo(MenuTo menuTo) {
        return new Menu(null, menuTo.getMenuDescription(), DateUtil.toDate(menuTo.getDate()));
    }

    public static Menu updateFromTo(Menu menu, MenuTo menuTo) {
        menu.setDescription(menuTo.getMenuDescription());
        menu.setDate(DateUtil.toDate(menuTo.getDate()));
        return menu;
    }
}
