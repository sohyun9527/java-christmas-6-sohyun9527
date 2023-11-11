package christmas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Discount {
    private static final int FIRST_DAY = 1;
    private static final int MINIMUM_SALE = 1000;
    private static final int SALE_PRICE = 2023;
    private final Map<MenuBoard, Integer> bill;

    public Discount(Map<MenuBoard, Integer> bill) {
        this.bill = bill;
    }

    public List<Long> discountResult(long price, Date date) {
        List<Long> result = new ArrayList<>();
        result.add(christmas(date));
        result.add(commonDate(date));
        result.add(weekendDate(date));
        result.add(starDate(price, date));

        return result;
    }

    public long christmas(Date date) {
        if (date.isBeforeChristmas()) {
            return MINIMUM_SALE + (date.getDate() - FIRST_DAY) * 100L;
        }
        return 0;
    }

    public long commonDate(Date date) {
        if (date.isCommonDate()) {
            return dessertCount() * SALE_PRICE;
        }
        return 0;
    }

    public long starDate(long beforeDiscountPrice, Date date) {
        if (date.isStarDate()) {
            return beforeDiscountPrice - MINIMUM_SALE;
        }
        return 0;
    }

    public long weekendDate(Date date) {
        if (date.isWeekend()) {
            return mainCount() * SALE_PRICE;
        }
        return 0;
    }

    public long beforeSalePrice() {
        return bill.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public long dessertCount() {
        return bill.entrySet().stream()
                .filter(entry -> entry.getKey().getCategory() == Category.DESSERT)
                .count();
    }

    public long mainCount() {
        return bill.entrySet().stream()
                .filter(entry -> entry.getKey().getCategory() == Category.MAIN)
                .count();
    }

}
