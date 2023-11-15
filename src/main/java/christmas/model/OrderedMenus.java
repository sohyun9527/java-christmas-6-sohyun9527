package christmas.model;

import christmas.model.exception.CategoryException;
import christmas.model.exception.OrderException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderedMenus {
    private static final int MAXIMUM_ORDER_QUANTITY = 20;
    private final List<OrderedMenu> menus;

    public OrderedMenus(List<OrderedMenu> menus) {
        validate(menus);
        this.menus = menus;
    }

    public void validate(List<OrderedMenu> menus) {
        validateDuplicateMane(menus);
        validateOverQuantity(menus);
        validateAllCategoryIsDrink(menus);
    }

    public long totalAmount() {
        return menus.stream()
                .mapToLong(OrderedMenu::calculateMenuPrice)
                .sum();
    }

    public long countDessertCategory() {
        return menus.stream()
                .filter(OrderedMenu::isDessertCategory)
                .mapToInt(OrderedMenu::getQuantity)
                .sum();
    }

    public long countMainCategory() {
        return menus.stream()
                .filter(OrderedMenu::isMainCategory)
                .mapToInt(OrderedMenu::getQuantity)
                .sum();
    }

    public void validateDuplicateMane(List<OrderedMenu> menus) {
        Set<Menu> uniqueMenus = menus.stream().map(OrderedMenu::getMenu)
                .collect(Collectors.toSet());
        if (uniqueMenus.size() != menus.size()) {
            throw new OrderException();
        }
    }

    private void validateAllCategoryIsDrink(List<OrderedMenu> orderedMenus) {
        boolean allAreDrinks = orderedMenus.stream()
                .allMatch(OrderedMenu::isDrinkCategory);

        if (allAreDrinks) {
            throw new CategoryException(Category.DRINK);
        }
    }

    private void validateOverQuantity(List<OrderedMenu> orderedMenus) {
        int count = orderedMenus.stream()
                .mapToInt(OrderedMenu::getQuantity)
                .sum();
        if (count > MAXIMUM_ORDER_QUANTITY) {
            throw new OrderException();
        }
    }

}
