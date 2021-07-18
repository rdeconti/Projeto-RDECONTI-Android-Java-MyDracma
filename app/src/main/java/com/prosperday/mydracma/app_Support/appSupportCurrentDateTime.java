package com.prosperday.mydracma.app_Support;
// ---------------------------------------------------------------
// Created by Rosemeire Deconti
// Created on 23/03/2018
// Created at 14:22
// Package is com.prosperday.mydracma
// Project is MyDracma
// ----------------------------------------------------------------

import java.util.Calendar;

public class appSupportCurrentDateTime {

    static final Calendar myNow = Calendar.getInstance();

    private static final int i_myYear = myNow.get(Calendar.YEAR);
    private static final int i_myMonth = myNow.get(Calendar.MONTH);
    private static final int i_myDay = myNow.get(Calendar.DAY_OF_MONTH);

    public static final String myYear = Integer.toString(i_myYear);
    public static final String myMonth = Integer.toString(i_myMonth);
    public static final String myDay = Integer.toString(i_myDay);

    private static final int i_myHour = myNow.get(Calendar.HOUR_OF_DAY);
    private static final int i_myMinute = myNow.get(Calendar.MINUTE);
    private static final int i_mySecond = myNow.get(Calendar.SECOND);

    public static final String myHour = Integer.toString(i_myHour);
    public static final String myMinute = Integer.toString(i_myMinute);
    public static final String mySecond = Integer.toString(i_mySecond);

    public static final String myDate = i_myDay + "/" + i_myMonth + "/" + i_myYear;
    public static final String myTime = i_myHour + ":" + i_myMinute + ":" + i_mySecond;
    public static final String myDateTime = myDate + " " + myTime;
    public static final String myStartDate = myYear + "/" + myMonth + "/" + myDay;
    public static final String myPlannedDate = myYear + "/" + myMonth + "/" + myDay;
    public static final String myFinishDate = String.valueOf(i_myYear + 10) + "/" + myMonth + "/" + myDay;
    public static final String myExpireDate = String.valueOf(i_myYear + 10) + "/" + myMonth + "/" + myDay;
    public static final String myRealizedDate = myYear + "/" + String.valueOf(i_myMonth + 10) + "/" + myDay;

}
