package christmas.model;


import christmas.model.exception.DateException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class EventDate {
    private static final int CHRISTMAS_DATE = 25;
    private static final int YEAR = 2023;
    private static final int START_DAY = 1;
    private static final int END_DAY = 31;
    private final int date;

    public EventDate(int date) {
        validateDateRange(date);
        this.date = date;
    }

    private void validateDateRange(int date) {
        if (date < START_DAY || date > END_DAY) {
            throw new DateException();
        }
    }

    public boolean isBeforeChristmas() {
        return date <= 25;
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = getLocalDate();
        return dayOfWeek.equals(DayOfWeek.FRIDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY);
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
