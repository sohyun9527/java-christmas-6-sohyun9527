package christmas.model;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class Date {
    private static final int CHRISTMAS_DATE = 25;
    private static final int YEAR = 2023;
    private final int date;

    public Date(int date) {
        validateDateRange(date);
        this.date = date;
    }

    private void validateDateRange(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("1 ~ 31일 중 날짜를 택해주세요.");
        }
    }

    public boolean isBeforeChristmas() {
        return date <= 25;
    }

    public boolean isCommonDate() {
        DayOfWeek day = getLocalDate();
        return day != DayOfWeek.FRIDAY && day != DayOfWeek.SATURDAY;
    }

    public boolean isWeekend() {
        return getLocalDate().equals(DayOfWeek.FRIDAY)
                || getLocalDate().equals(DayOfWeek.SATURDAY);
    }

    public boolean isStarDate() {
        return getLocalDate().equals(DayOfWeek.SUNDAY) || date == CHRISTMAS_DATE;
    }

    public DayOfWeek getLocalDate() {
        LocalDate localDate = LocalDate.of(YEAR, Month.DECEMBER, date);
        return localDate.getDayOfWeek();
    }

    public int getDate() {
        return date;
    }
}
