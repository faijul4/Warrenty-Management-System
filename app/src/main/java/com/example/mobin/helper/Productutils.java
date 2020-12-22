package com.example.mobin.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Productutils {
    public static String getDateWithTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
