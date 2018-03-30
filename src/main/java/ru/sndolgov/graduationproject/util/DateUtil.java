package ru.sndolgov.graduationproject.util;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    public static final String DATE_PATTERN = "yyyy-MM-dd";


    public static Date of(int year, int month, int date) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(year, month - 1, date, 0, 0, 0);
        return calendar.getTime();
    }

    public static Date getDateToday(){
        return toDate(LocalDate.now());
    }

    public static LocalDate toLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date toDate(LocalDate ld){
        return of(ld.getYear(),ld.getMonthValue(), ld.getDayOfMonth());
    }

    public static int compareDate (Date date1, Date date2){
        return toLocalDate(date1).compareTo(toLocalDate(date2));
    }
}
