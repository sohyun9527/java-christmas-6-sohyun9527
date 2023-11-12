package christmas.model;

import java.util.ArrayList;
import java.util.List;

public class Discount {
    private static final int FIRST_DAY = 1;
    private static final int MINIMUM_UNIT = 1000;
    private static final int SALE_PRICE = 2023;
    private static final int PROMOTION_PRICE = 120_000;
    private static final int MINIMUM_ORDER_PRICE = 10_000;

    private final List<Menu> orders;
    private long totalDiscount;

    public Discount(List<Menu> order) {
        this.orders = order;
    }

    public List<Long> result(long price, EventDate eventDate) {
        List<Long> result = new ArrayList<>();
        if (isOverEventPrice(price)) {
            result.add(christmas(eventDate));
            result.add(weekDay(eventDate));
            result.add(weekend(eventDate));
            result.add(starDate(eventDate));
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

    private long starDate(EventDate eventDate) {
        if (eventDate.isStarDate()) {
            long discountPrice = MINIMUM_UNIT;
            totalDiscount += discountPrice;
            return discountPrice;
        }
        return 0;
    }

    private long christmas(EventDate eventDate) {
        if (eventDate.isBeforeChristmas()) {
            long discountPrice = MINIMUM_UNIT + (eventDate.getDate() - FIRST_DAY) * 100L;
            totalDiscount += discountPrice;
            return discountPrice;
        }
        return 0;
    }

    private long weekDay(EventDate eventDate) {
        if (eventDate.isCommonDate()) {
            long discountPrice = dessertCount() * SALE_PRICE;
            totalDiscount += discountPrice;
            return discountPrice;
        }
        return 0;
    }

    private long weekend(EventDate eventDate) {
        if (eventDate.isWeekend()) {
            long discountPrice = mainCount() * SALE_PRICE;
            totalDiscount += discountPrice;
            return discountPrice;
        }
        return 0;
    }

    private long dessertCount() {
        return orders.stream().filter(Menu::isDessert).count();
    }

    private long mainCount() {
        return orders.stream().filter(Menu::isMain).count();
    }

    public long getTotalDiscount() {
        return totalDiscount;
    }
}
