package org.example.Dates;

public enum DayInWeek {
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7);

    final int number;

    DayInWeek(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
