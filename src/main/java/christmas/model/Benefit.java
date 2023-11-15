package christmas.model;

import christmas.repository.MenuBoard;
import java.util.EnumMap;

public class Benefit {
    private static final int FIRST_DAY = 1;
    private static final int MINIMUM_UNIT = 1000;
    private static final int SALE_PRICE = 2023;
    private static final int PROMOTION_PRICE = 120_000;
    private static final int MINIMUM_ORDER_PRICE = 10_000;
    private static final long DAY_DISCOUNT = 100L;

    private long totalBenefitAmount;

    public EnumMap<DiscountType, Long> result(EventDay eventDay, OrderedMenus menus) {
        EnumMap<DiscountType, Long> result = new EnumMap<>(DiscountType.class);
        if (isOverEventPrice(menus.totalAmount())) {
            result.put(DiscountType.CHRISTMAS, christmas(eventDay));
            result.put(DiscountType.WEEKDAY, weekDay(eventDay, menus));
            result.put(DiscountType.WEEKEND, weekend(eventDay, menus));
            result.put(DiscountType.SPECIAL, special(eventDay));
            result.put(DiscountType.PROMOTION, promotion(menus.totalAmount()));
        }
        return result;
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

    private long christmas(EventDay eventDay) {
        if (eventDay.isBeforeChristmas()) {
            long discountPrice = MINIMUM_UNIT + (eventDay.getDay() - FIRST_DAY) * DAY_DISCOUNT;
            totalBenefitAmount -= discountPrice;
            return -discountPrice;
        }
        return 0;
    }

    private long weekend(EventDay eventDay, OrderedMenus menus) {
        if (eventDay.isWeekend()) {
            long discountPrice = menus.countMainCategory() * SALE_PRICE;
            totalBenefitAmount -= discountPrice;
            return -discountPrice;
        }
        return 0;
    }

    private long weekDay(EventDay eventDay, OrderedMenus menus) {
        if (!eventDay.isWeekend()) {
            long discountPrice = menus.countDessertCategory() * SALE_PRICE;
            totalBenefitAmount -= discountPrice;
            return -discountPrice;
        }
        return 0;
    }

    private long special(EventDay eventDay) {
        if (eventDay.isSpecialDay()) {
            long discountPrice = MINIMUM_UNIT;
            totalBenefitAmount -= discountPrice;
            return -discountPrice;
        }
        return 0;
    }

    public long getTotalBenefitAmount() {
        return totalBenefitAmount;
    }
}
