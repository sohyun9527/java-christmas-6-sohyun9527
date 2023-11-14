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
    private long totalBenefitAmount = 0;

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

    public MenuBoard champagnePromotion(long price) {
        if (price >= PROMOTION_PRICE) {
            return MenuBoard.CHAMPAGNE;
        }
        return MenuBoard.NONE;
    }

    private long promotion(long price) {
        if (price >= PROMOTION_PRICE) {
            return -MenuBoard.CHAMPAGNE.getPrice();
        }
        return 0;
    }

    private boolean isOverEventPrice(long price) {
        return price >= MINIMUM_ORDER_PRICE;
    }

    private long starDate(EventDate eventDate) {
        if (eventDate.isStarDate()) {
            long discountPrice = MINIMUM_UNIT;
            totalBenefitAmount -= discountPrice;
            return -discountPrice;
        }
        return 0;
    }

    private long christmas(EventDate eventDate) {
        if (eventDate.isBeforeChristmas()) {
            long discountPrice = MINIMUM_UNIT + (eventDate.getDate() - FIRST_DAY) * 100L;
            totalBenefitAmount -= discountPrice;
            return -discountPrice;
        }
        return 0;
    }

    private long weekDay(EventDate eventDate) {
        if (!eventDate.isWeekend()) {
            long discountPrice = dessertCategoryCount() * SALE_PRICE;
            totalBenefitAmount -= discountPrice;
            return -discountPrice;
        }
        return 0;
    }

    private long weekend(EventDate eventDate) {
        if (eventDate.isWeekend()) {
            long discountPrice = mainCategoryCount() * SALE_PRICE;
            totalBenefitAmount -= discountPrice;
            return -discountPrice;
        }
        return 0;
    }

    private long dessertCategoryCount() {
        return orders.stream()
                .filter(Menu::isDessertCategory)
                .mapToLong(Menu::getCount)
                .sum();
    }

    private long mainCategoryCount() {
        return orders.stream()
                .filter(Menu::isMainCategory)
                .mapToLong(Menu::getCount)
                .sum();
    }

    public long getTotalBenefitAmount() {
        return totalBenefitAmount;
    }
}
