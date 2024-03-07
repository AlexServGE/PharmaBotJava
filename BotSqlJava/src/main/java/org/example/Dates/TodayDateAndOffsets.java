package org.example.Dates;

import lombok.Getter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TodayDateAndOffsets {

    static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final Calendar cal;

    @Getter
    final private String todayDate;

    @Getter
    final private String todayDateOffset1;

    @Getter
    final private String todayDateOffset2;

    @Getter
    final private String todayDateOffset3;

    @Getter
    final private String todayDateOffset4;

    @Getter
    final private int dayOfWeek;

    {
        this.cal = Calendar.getInstance();
        this.todayDate = getStringDate(generateTodaysDate());
        this.todayDateOffset1 = getStringDate(generateTodayDateOffset1());
        this.todayDateOffset2 = getStringDate(generateTodayDateOffset2());
        this.todayDateOffset3 = getStringDate(generateTodayDateOffset3());
        this.todayDateOffset4 = getStringDate(generateTodayDateOffset4());
        this.dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
    }


    private Date generateTodaysDate() {
        return this.cal.getTime();
    }

    private Date generateTodayDateOffset1() {
        return new Date(generateTodaysDate().getTime() - 24 * 60 * 60 * 1000);
    }

    private Date generateTodayDateOffset2() {
        return new Date(generateTodayDateOffset1().getTime() - 24 * 60 * 60 * 1000);
    }

    private Date generateTodayDateOffset3() {
        return new Date(generateTodayDateOffset2().getTime() - 24 * 60 * 60 * 1000);
    }

    private Date generateTodayDateOffset4() {
        return new Date(generateTodayDateOffset3().getTime() - 24 * 60 * 60 * 1000);
    }

    private String getStringDate(Date date) {
        return dateFormat.format(date.getTime());
    }
}
