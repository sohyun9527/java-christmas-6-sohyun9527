package christmas.model;


import christmas.model.exception.DateException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class EventDay {
    private static final int CHRISTMAS_DATE = 25;
    private static final int YEAR = 2023;
    private static final int START_DAY = 1;
    private static final int END_DAY = 31;
    private final int day;

    public EventDay(int day) {
        validateDateRange(day);
        this.day = day;
    }

    private void validateDateRange(int date) {
        if (date < START_DAY || date > END_DAY) {
            throw new DateException();
        }
    }

    public boolean isBeforeChristmas() {
        return day <= 25;
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = getLocalDate();
        return dayOfWeek.equals(DayOfWeek.FRIDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY);
    }

    public boolean isSpecialDay() {
        return getLocalDate().equals(DayOfWeek.SUNDAY) || day == CHRISTMAS_DATE;
    }

    private DayOfWeek getLocalDate() {
        LocalDate localDate = LocalDate.of(YEAR, Month.DECEMBER, day);
        return localDate.getDayOfWeek();
    }

    public int getDay() {
        return day;
    }
}
