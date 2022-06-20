package com.migros.couriertracking.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date today(){
        return new Date();
    }

    public static Date stringToDate(String dateStr) throws ParseException {
        return new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
    }

}