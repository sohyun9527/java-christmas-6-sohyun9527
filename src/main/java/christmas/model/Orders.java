package christmas.model;

import static christmas.view.message.ErrorMessage.CANT_ONLY_DRINKS_ORDER;
import static christmas.view.message.ErrorMessage.INVALID_ORDER;
import static christmas.view.message.ErrorMessage.OVER_QUANTITY_ORDER;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Orders {
    private static final int MAXIMUM_COUNT = 20;
    private List<Menu> menus;

    public Orders(List<Map<String, Integer>> orders) {
        validateDuplicateMenu(orders);
        validateOverCount(orders);
        validateAllOrdersAreDrinks(orders);
        makeMenus(orders);
    }

    public long totalAmount() {
        return menus.stream()
                .mapToLong(menu ->
                        (long) MenuBoard.getByName(menu.getName()).getPrice() * menu.getCount())
                .sum();
    }

    public void makeMenus(List<Map<String, Integer>> orders) {
        menus = orders.stream()
                .flatMap(order -> order.entrySet().stream())
                .map(entry -> new Menu(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public void validateDuplicateMenu(List<Map<String, Integer>> orders) {
        Set<String> menuNames = orders.stream()
                .flatMap(order -> order.keySet().stream())
                .collect(Collectors.toSet());

        if (menuNames.size() != orders.size()) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    public void validateAllOrdersAreDrinks(List<Map<String, Integer>> orders) {
        boolean allOrdersAreDrinks = orders.stream()
                .flatMap(order -> order.keySet().stream())
                .allMatch(menuName -> {
                    MenuBoard menuBoard = MenuBoard.getByName(menuName);
                    return menuBoard.getCategory() == Category.DRINK;
                });
        if (allOrdersAreDrinks) {
            throw new IllegalArgumentException(CANT_ONLY_DRINKS_ORDER.getMessage());
        }
    }

    public void validateOverCount(List<Map<String, Integer>> orders) {
        int sum = orders.
                stream()
                .mapToInt(order
                        -> order.values().stream()
                        .mapToInt(Integer::intValue)
                        .sum())
                .sum();
        if (sum > MAXIMUM_COUNT) {
            throw new IllegalArgumentException(OVER_QUANTITY_ORDER.getMessage());
        }
    }

    public List<Menu> getMenus() {
        return Collections.unmodifiableList(menus);
    }
}
