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

    private final EnumMap<DiscountType, Long> result = new EnumMap<>(DiscountType.class);
    private final OrderedMenus orderedMenus;
    private final EventDay eventDay;
    private long totalDiscountAmount;

    public Benefit(EventDay eventDay, OrderedMenus orderedMenus) {
        this.eventDay = eventDay;
        this.orderedMenus = orderedMenus;
        makeResult();
    }

    public void makeResult() {
        if (isOverEventPrice(orderedMenus.totalAmount())) {
            result.put(DiscountType.CHRISTMAS, christmas(eventDay));
            result.put(DiscountType.WEEKDAY, weekDay(eventDay, orderedMenus));
            result.put(DiscountType.WEEKEND, weekend(eventDay, orderedMenus));
            result.put(DiscountType.SPECIAL, special(eventDay));
            result.put(DiscountType.PROMOTION, promotion(orderedMenus.totalAmount()));
        }
    }

    public long totalBenefitAmount() {
        return result.values().stream().mapToLong(Long::longValue).sum();
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
            totalDiscountAmount -= discountPrice;
            return -discountPrice;
        }
        return 0;
    }

    private long weekend(EventDay eventDay, OrderedMenus menus) {
        if (eventDay.isWeekend()) {
            long discountPrice = menus.countMainCategory() * SALE_PRICE;
            totalDiscountAmount -= discountPrice;
            return -discountPrice;
        }
        return 0;
    }

    private long weekDay(EventDay eventDay, OrderedMenus menus) {
        if (!eventDay.isWeekend()) {
            long discountPrice = menus.countDessertCategory() * SALE_PRICE;
            totalDiscountAmount -= discountPrice;
            return -discountPrice;
        }
        return 0;
    }

    private long special(EventDay eventDay) {
        if (eventDay.isSpecialDay()) {
            long discountPrice = MINIMUM_UNIT;
            totalDiscountAmount -= discountPrice;
            return -discountPrice;
        }
        return 0;
    }

    public EnumMap<DiscountType, Long> getResult() {
        return result;
    }

    public long getTotalDiscountAmount() {
        return totalDiscountAmount;
    }
}
