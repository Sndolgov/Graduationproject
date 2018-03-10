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
       return new MenuTo(menu.getId(), menu.getDescription(), menu.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}
