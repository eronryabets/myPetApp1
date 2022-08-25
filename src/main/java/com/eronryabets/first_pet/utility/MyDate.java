package com.eronryabets.first_pet.utility;


import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.util.Calendar.*;

public class MyDate {
    static final Calendar currentCalendar = Calendar.getInstance();


    public static int getCurrentYear(){
        return currentCalendar.get(Calendar.YEAR);
    }

    public static int getCurrentMonth(){
        return currentCalendar.get(MONTH)+1;
    }

    public static int maxDayInMonth(int year,int month){
        int iYear = year;
        int iDay = 1;
        Calendar myCalendar;
        int maxDays;
        switch (month){
            case 1 :
                 myCalendar = new GregorianCalendar(iYear, JANUARY, iDay);
                 maxDays = myCalendar.getActualMaximum(DAY_OF_MONTH);
                 break;
            case 2 :
                 myCalendar = new GregorianCalendar(iYear, FEBRUARY, iDay);
                 maxDays = myCalendar.getActualMaximum(DAY_OF_MONTH);
                 break;
            case 3 :
                myCalendar = new GregorianCalendar(iYear, MARCH, iDay);
                maxDays = myCalendar.getActualMaximum(DAY_OF_MONTH);
                break;
            case 4 :
                myCalendar = new GregorianCalendar(iYear, APRIL, iDay);
                maxDays = myCalendar.getActualMaximum(DAY_OF_MONTH);
                break;
            case 5 :
                myCalendar = new GregorianCalendar(iYear, MAY, iDay);
                maxDays = myCalendar.getActualMaximum(DAY_OF_MONTH);
                break;
            case 6 :
                myCalendar = new GregorianCalendar(iYear, JUNE, iDay);
                maxDays = myCalendar.getActualMaximum(DAY_OF_MONTH);
                break;
            case 7 :
                myCalendar = new GregorianCalendar(iYear, JULY, iDay);
                maxDays = myCalendar.getActualMaximum(DAY_OF_MONTH);
                break;
            case 8 :
                myCalendar = new GregorianCalendar(iYear, AUGUST, iDay);
                maxDays = myCalendar.getActualMaximum(DAY_OF_MONTH);
                break;
            case 9 :
                myCalendar = new GregorianCalendar(iYear, SEPTEMBER, iDay);
                maxDays = myCalendar.getActualMaximum(DAY_OF_MONTH);
                break;
            case 10 :
                myCalendar = new GregorianCalendar(iYear, OCTOBER, iDay);
                maxDays = myCalendar.getActualMaximum(DAY_OF_MONTH);
                break;
            case 11 :
                myCalendar = new GregorianCalendar(iYear, NOVEMBER, iDay);
                maxDays = myCalendar.getActualMaximum(DAY_OF_MONTH);
                break;
            case 12 :
                myCalendar = new GregorianCalendar(iYear, DECEMBER, iDay);
                maxDays = myCalendar.getActualMaximum(DAY_OF_MONTH);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + month);
        }
        return maxDays;
    }

}

