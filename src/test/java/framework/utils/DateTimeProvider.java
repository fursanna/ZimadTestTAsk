package framework.utils;


import project.enums.DateTimeFormats;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeProvider {

    public static String getDateTimeByFormatAndOffset(DateTimeFormats format, int daysOffset) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format.toString());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, daysOffset);
        return dateFormat.format(calendar.getTime());
    }
}