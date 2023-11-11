package christmas.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

public class Discount {
    private static final int YEAR = 2023;
    private final Map<MenuBoard, Integer> bill;
    private final int visitDate;

    public Discount(Map<MenuBoard, Integer> bill, int date) {
        this.bill = bill;
        this.visitDate = date;
    }

    public long beforeSalePrice() {
        return bill.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public DayOfWeek getLocalDate() {
        LocalDate localDate = LocalDate.of(YEAR, Month.DECEMBER, visitDate);
        return localDate.getDayOfWeek();
    }
}
