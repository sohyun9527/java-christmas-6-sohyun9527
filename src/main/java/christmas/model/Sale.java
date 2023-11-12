package christmas.model;

import java.util.List;

public class Sale {
    private static final int FIRST_DAY = 1;
    private static final int MINIMUM_UNIT = 1000;
    private static final int SALE_PRICE = 2023;
    private static final int PROMOTION_PRICE = 120_000;
    private static final int MINIMUM_ORDER_PRICE = 10_000;

    private final List<Menu> orders;
    private long totalDiscount;

    public Sale(List<Menu> order) {
        this.orders = order;
    }

    private long dessertCount() {
        return orders.stream()
                .filter(Menu::isDessert)
                .count();
    }

    private long mainCount() {
        return orders.stream()
                .filter(Menu::isMain)
                .count();
    }
}
