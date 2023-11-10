package christmas.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @DisplayName("존재하지 않는 메뉴일 시 에러 반환")
    @Test
    public void notExistMenu() {
        String menu = "감자탕";
        int count = 1;

        assertThrows(IllegalArgumentException.class, () -> new Menu(menu, count));
    }

    @DisplayName("올바른 메뉴 주문 기능 테스트")
    @Test
    public void existMenu() {
        String name = "제로콜라";
        int count = 2;
        assertDoesNotThrow(() -> {
            Menu menu = new Menu(name, count);
        });
    }

    @DisplayName("0개 주문 시 에러 반환")
    @Test
    public void underMinimumCountValidation() {
        String name = "제로콜라";
        int count = 0;
        assertThrows(IllegalArgumentException.class, () -> new Menu(name, count));
    }
}