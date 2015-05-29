package com.ryandg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ryan De Gruyter on 27/05/2015.
 */
public class DateUtils {
    public static final String DATE_FORMAT = "yyyyMMdd";

    public static String formatDateForDb(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        return simpleDateFormat.format(date);
    }

    public static Date convertStringToDate(String date) {
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat(DATE_FORMAT);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCurrentDateInDbFormat() {
        Calendar calendar = Calendar.getInstance();
        final Date time = calendar.getTime();
        return formatDateForDb(time);
    }
}
