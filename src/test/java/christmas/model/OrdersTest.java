package christmas.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrdersTest {

    @DisplayName("중복된 메뉴명이 있을 시 예외 반환")
    @Test
    void duplicateMenuOrder() {
        List<Map<String, Integer>> duplicateOrder = List.of(
                Map.of("양송이수프", 1),
                Map.of("타파스", 1),
                Map.of("양송이수프", 1)
        );

        assertThrows(IllegalArgumentException.class, () -> new Orders(duplicateOrder));
    }

    @DisplayName("총 합계가 20개가 넘을 경우 예외 반환")
    @Test
    void overCountMenuOrder() {
        List<Map<String, Integer>> overCountOrder = List.of(
                Map.of("양송이수프", 7),
                Map.of("타파스", 11),
                Map.of("티본스테이크", 5)
        );

        assertThrows(IllegalArgumentException.class, () -> new Orders(overCountOrder));
    }

    @DisplayName("음료만 주문했을 시 예외 반환")
    @Test
    void onlyDrinksMenuOrder() {
        List<Map<String, Integer>> onlyDrinks = List.of(
                Map.of("제로콜라", 3),
                Map.of("레드와인", 1),
                Map.of("샴페인", 2)
        );
        assertThrows(IllegalArgumentException.class, () -> new Orders(onlyDrinks));
    }

    @DisplayName("올바른 주문이 들어왔을 시 예외가 발생하지 않는다")
    @Test
    void validOrders() {
        List<Map<String, Integer>> validMenus = List.of(
                Map.of("타파스", 3),
                Map.of("레드와인", 1),
                Map.of("샴페인", 2)
        );

        assertDoesNotThrow(() -> {
            Orders orders = new Orders(validMenus);
        });
    }
}