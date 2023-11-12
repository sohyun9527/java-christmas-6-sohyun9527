package christmas.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @DisplayName("메뉴의 카테고리가 디저트라면 true 반환")
    @Test
    public void isDessertCategory() {
        Menu dessertMenu = new Menu("초코케이크", 3);
        Menu appetizerMenu = new Menu("시저샐러드", 4);
        Menu mainMenu = new Menu("티본스테이크", 1);

        assertTrue(dessertMenu.isDessertCategory());
        assertFalse(mainMenu.isDessertCategory());
        assertFalse(appetizerMenu.isDessertCategory());
    }

    @DisplayName("메뉴의 카테고리가 디저트라면 true 반환")
    @Test
    public void isMainCategory() {
        Menu dessertMenu = new Menu("초코케이크", 3);
        Menu appetizerMenu = new Menu("시저샐러드", 4);
        Menu mainMenu = new Menu("티본스테이크", 1);

        assertTrue(mainMenu.isMainCategory());
        assertFalse(dessertMenu.isMainCategory());
        assertFalse(appetizerMenu.isMainCategory());
    }
}