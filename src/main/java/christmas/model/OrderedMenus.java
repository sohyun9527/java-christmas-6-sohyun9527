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

    // 중복 검사
    // 음료 카테고리 검사
    // 총 개수 검사
    // 총 금액 반환
    // 총 메인 카테고리 개수 반환
    // 총 디저트 카테고리 개수 반환
}
