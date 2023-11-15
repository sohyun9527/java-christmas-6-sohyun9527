package christmas.model;

import christmas.model.exception.OrderException;

public class OrderedMenu {
    private static final int MINIMUM_QUANTITY = 1;
    private final Menu menu;
    private final int quantity;

    public OrderedMenu(Menu menu, int quantity) {
        this.menu = menu;
        validateQuantityCount(quantity);
        this.quantity = quantity;
    }

    public void validateQuantityCount(int quantity) {
        if (quantity < MINIMUM_QUANTITY) {
            throw new OrderException();
        }
    }

    public boolean isDrinkCategory() {
        return menu.isDrinkCategory();
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }
}
