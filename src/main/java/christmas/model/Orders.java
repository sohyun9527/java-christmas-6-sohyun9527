package christmas.model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Orders {
    private List<Menu> menus;

    public Orders(List<Map<String, Integer>> orders) {
        validateDuplicateMenu();
        validateOverCount(orders);
        makeMenus(orders);
    }

    public void makeMenus(List<Map<String, Integer>> orders) {
        menus = orders.stream()
                .flatMap(order -> order.entrySet().stream())
                .map(entry -> new Menu(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public void validateDuplicateMenu() {
        Set<String> uniqueNames = menus.stream()
                .map(Menu::getName)
                .collect(Collectors.toSet());

        if (uniqueNames.size() != menus.size()) {
            throw new IllegalArgumentException("중복된 메뉴가 존재합니다.");
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
        if (sum > 20) {
            throw new IllegalArgumentException("20개를 초과해서 주문하지마셈");
        }
    }
}
