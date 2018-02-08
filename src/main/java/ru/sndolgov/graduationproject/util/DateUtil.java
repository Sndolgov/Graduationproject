package ru.sndolgov.graduationproject.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
   public static Date of(int year, int month, int date){
       Calendar calendar = new GregorianCalendar();
       SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");

       calendar.set(year, month-1, date, 0, 0, 0);
       return calendar.getTime();
   }
}
