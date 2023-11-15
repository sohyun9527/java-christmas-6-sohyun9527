package christmas.model;

import christmas.model.exception.CategoryException;
import christmas.model.exception.OrderException;
import christmas.model.exception.OverQuantityException;
import java.util.List;

public class OrderedMenus {
    private static final int MAXIMUM_ORDER_QUANTITY = 20;
    private final List<OrderedMenu> orderedMenus;

    public OrderedMenus(List<OrderedMenu> orderedMenus) {
        validate(orderedMenus);
        this.orderedMenus = orderedMenus;
    }

    private void validate(List<OrderedMenu> menus) {
        validateDuplicateMane(menus);
        validateOverQuantity(menus);
        validateAllCategoryIsDrink(menus);
    }

    public long totalAmount() {
        return orderedMenus.stream()
                .mapToLong(OrderedMenu::calculateMenuPrice)
                .sum();
    }

    public long countDessertCategory() {
        return orderedMenus.stream()
                .filter(OrderedMenu::isDessertCategory)
                .mapToInt(OrderedMenu::getQuantity)
                .sum();
    }

    public long countMainCategory() {
        return orderedMenus.stream()
                .filter(OrderedMenu::isMainCategory)
                .mapToInt(OrderedMenu::getQuantity)
                .sum();
    }

    private void validateDuplicateMane(List<OrderedMenu> menus) {
        long uniqueMenuSize = menus.stream().distinct().count();
        
        if (uniqueMenuSize != menus.size()) {
            throw new OrderException();
        }
    }

    private void validateOverQuantity(List<OrderedMenu> orderedMenus) {
        int count = orderedMenus.stream()
                .mapToInt(OrderedMenu::getQuantity)
                .sum();
        if (count > MAXIMUM_ORDER_QUANTITY) {
            throw new OverQuantityException(MAXIMUM_ORDER_QUANTITY);
        }
    }

    private void validateAllCategoryIsDrink(List<OrderedMenu> orderedMenus) {
        boolean allAreDrinks = orderedMenus.stream()
                .allMatch(OrderedMenu::isDrinkCategory);

        if (allAreDrinks) {
            throw new CategoryException(Category.DRINK);
        }
    }

    public List<OrderedMenu> getOrderedMenus() {
        return orderedMenus;
    }
}
