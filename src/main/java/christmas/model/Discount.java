package christmas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Discount {
    private static final int FIRST_DAY = 1;
    private static final int MINIMUM_UNIT = 1000;
    private static final int SALE_PRICE = 2023;
    private static final int PROMOTION_PRICE = 120000;
    private static final int MINIMUM_ORDER_PRICE = 10000;
    private final Map<MenuBoard, Integer> bill;
    private long totalDiscount;

    public Discount(Map<MenuBoard, Integer> bill) {
        this.bill = bill;
    }

    public List<Long> result(long price, Date date) {
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

    private long promotion(long price) {
        if (price >= PROMOTION_PRICE) {
            return MenuBoard.CHAMPAGNE.getPrice();
        }
        return 0;
    }

    private boolean isOverEventPrice(long price) {
        return price >= MINIMUM_ORDER_PRICE;
    }

    private long christmas(Date date) {
        if (date.isBeforeChristmas()) {
            long discountPrice = MINIMUM_UNIT + (date.getDate() - FIRST_DAY) * 100L;
            totalDiscount += discountPrice;
            return discountPrice;
        }
        return 0;
    }

    private long commonDate(Date date) {
        if (date.isCommonDate()) {
            long discountPrice = dessertCount() * SALE_PRICE;
            totalDiscount += discountPrice;
            return discountPrice;
        }
        return 0;
    }

    private long starDate(Date date) {
        if (date.isStarDate()) {
            long discountPrice = MINIMUM_UNIT;
            totalDiscount += discountPrice;
            return discountPrice;
        }
        return 0;
    }

    private long weekendDate(Date date) {
        if (date.isWeekend()) {
            long discountPrice = mainCount() * SALE_PRICE;
            totalDiscount += discountPrice;
            return discountPrice;
        }
        return 0;
    }

    private long dessertCount() {
        return bill.entrySet().stream()
                .filter(entry -> entry.getKey().getCategory() == Category.DESSERT)
                .mapToLong(Map.Entry::getValue)
                .sum();
    }

    private long mainCount() {
        return bill.entrySet().stream()
                .filter(entry -> entry.getKey().getCategory() == Category.MAIN)
                .mapToLong(Map.Entry::getValue)
                .sum();
    }

    public long getTotalDiscount() {
        return totalDiscount;
    }
}
