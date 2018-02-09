package ru.sndolgov.graduationproject.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    public static Date of(int year, int month, int date) {
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        calendar.set(year, month - 1, date, 0, 0, 0);
        return calendar.getTime();
    }

    public boolean copareDate(Date date1, Date date2) {
        LocalDate ld1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ld2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ld1.compareTo(ld2) == 0;
    }
}
