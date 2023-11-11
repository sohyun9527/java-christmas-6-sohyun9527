package christmas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Discount {
    private static final int FIRST_DAY = 1;
    private static final int MINIMUM_UNIT = 1000;
    private static final int SALE_PRICE = 2023;
    private final Map<MenuBoard, Integer> bill;
    private long totalDiscount;

    public Discount(Map<MenuBoard, Integer> bill) {
        this.bill = bill;
    }

    public List<Long> discountResult(long price, Date date) {
        List<Long> result = new ArrayList<>();
        if (isOverEventPrice(price)) {
            result.add(christmas(date));
            result.add(commonDate(date));
            result.add(weekendDate(date));
            result.add(starDate(date));
            result.add(promotion(price));
        }

        return result;
    }

    public long promotion(long price) {
        if (price >= 120000) {
            return MenuBoard.CHAMPAGNE.getPrice();
        }
        return 0;
    }

    public boolean isOverEventPrice(long price) {
        return price >= 10000;
    }

    public long christmas(Date date) {
        if (date.isBeforeChristmas()) {
            long discountPrice = MINIMUM_UNIT + (date.getDate() - FIRST_DAY) * 100L;
            totalDiscount += discountPrice;
            return discountPrice;
        }
        return 0;
    }

    public long commonDate(Date date) {
        if (date.isCommonDate()) {
            long discountPrice = dessertCount() * SALE_PRICE;
            totalDiscount += discountPrice;
            return discountPrice;
        }
        return 0;
    }

    public long starDate(Date date) {
        if (date.isStarDate()) {
            long discountPrice = MINIMUM_UNIT;
            totalDiscount += discountPrice;
            return discountPrice;
        }
        return 0;
    }

    public long weekendDate(Date date) {
        if (date.isWeekend()) {
            long discountPrice = mainCount() * SALE_PRICE;
            totalDiscount += discountPrice;
            return discountPrice;
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
                .mapToLong(Map.Entry::getValue)
                .sum();
    }

    public long mainCount() {
        return bill.entrySet().stream()
                .filter(entry -> entry.getKey().getCategory() == Category.MAIN)
                .mapToLong(Map.Entry::getValue)
                .sum();
    }

    public long getTotalDiscount() {
        return totalDiscount;
    }
}
