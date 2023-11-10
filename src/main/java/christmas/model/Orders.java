package christmas.model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Orders {
    private List<Menu> menus;

    public Orders(List<Map<String, Integer>> orders) {
        makeMenus(orders);
        validateDuplicateMenu();
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
}
