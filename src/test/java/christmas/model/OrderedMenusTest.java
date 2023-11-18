package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.model.exception.CategoryException;
import christmas.model.exception.OrderException;
import christmas.model.exception.OverQuantityException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderedMenusTest {
    Menu porkMenu;
    Menu duplicateDrink;
    Menu stake;
    Menu drink;
    Menu chicken;
    Menu drink1;
    Menu drink2;
    Menu dessert;

    @BeforeEach
    void setUp() {
        porkMenu = new Menu("돼지고기", 6000, Category.MAIN);
        duplicateDrink = new Menu("메론소다", 6000, Category.DRINK);
        stake = new Menu("소고기", 6000, Category.MAIN);
        drink = new Menu("메론소다", 6000, Category.DRINK);
        chicken = new Menu("치킨", 7000, Category.MAIN);
        drink1 = new Menu("콜라", 2000, Category.DRINK);
        drink2 = new Menu("사이다", 2500, Category.DRINK);
        dessert = new Menu("초코파이", 3000, Category.DESSERT);

    }

    @DisplayName("중복 메뉴 기능 검증 테스트")
    @Test
    void duplicateNameTest() {
        OrderedMenu porkOrder = new OrderedMenu(porkMenu, 3);
        OrderedMenu stakeOrder = new OrderedMenu(stake, 4);
        OrderedMenu drinkOrder = new OrderedMenu(drink, 2);
        OrderedMenu duplicateOrder = new OrderedMenu(duplicateDrink, 2);

        assertThatThrownBy(() -> new OrderedMenus(List.of(porkOrder, stakeOrder, drinkOrder, duplicateOrder)))
                .isInstanceOf(OrderException.class);
    }

    @DisplayName("총합 20개가 넘는다면 예외를 반환하는 기능 검증 테스트")
    @Test
    void overQuantityTest() {
        OrderedMenu porkOrder = new OrderedMenu(porkMenu, 10);
        OrderedMenu chickenOrder = new OrderedMenu(chicken, 11);

        assertThatThrownBy(() -> new OrderedMenus(List.of(porkOrder, chickenOrder)))
                .isInstanceOf(OverQuantityException.class);
    }

    @DisplayName("모든 메뉴가 음료인지 검증 테스트")
    @Test
    void allCategoryIsDrinkTest() {
        OrderedMenu drinkOrder1 = new OrderedMenu(drink1, 2);
        OrderedMenu drinkOrder2 = new OrderedMenu(drink2, 3);
        OrderedMenu dessertOrder = new OrderedMenu(dessert, 1);

        assertThatThrownBy(() -> new OrderedMenus(List.of(drinkOrder1, drinkOrder2)))
                .isInstanceOf(CategoryException.class);
        assertThatCode(() -> new OrderedMenus(List.of(drinkOrder1, drinkOrder2, dessertOrder)))
                .doesNotThrowAnyException();
    }

    @DisplayName("주문의 총합 반환 기능 테스트")
    @Test
    void totalAmount() {
        OrderedMenu porkOrder = new OrderedMenu(porkMenu, 3);
        OrderedMenu cokeOrder = new OrderedMenu(drink1, 2);
        OrderedMenus orderedMenus = new OrderedMenus(List.of(porkOrder, cokeOrder));

        assertThat(orderedMenus.totalAmount()).isEqualTo(6000 * 3 + 2000 * 2);
    }

    @DisplayName("주문의 디저트 카테고리 개수 반환 기능 테스트")
    @Test
    void countDessertCategory() {
        OrderedMenu dessert1 = new OrderedMenu(dessert, 3);
        OrderedMenu drinkOrder = new OrderedMenu(drink, 2);
        OrderedMenu mainOrder = new OrderedMenu(porkMenu, 2);
        OrderedMenus orderedMenus = new OrderedMenus(List.of(dessert1, drinkOrder, mainOrder));

        assertThat(orderedMenus.countDessertCategory()).isEqualTo(3);
    }

    @Test
    void countMainCategory() {
        OrderedMenu dessert1 = new OrderedMenu(dessert, 3);
        OrderedMenu drinkOrder = new OrderedMenu(drink, 2);
        OrderedMenu mainOrder = new OrderedMenu(porkMenu, 2);
        OrderedMenu mainOrder2 = new OrderedMenu(stake, 5);
        OrderedMenus orderedMenus = new OrderedMenus(List.of(dessert1, drinkOrder, mainOrder, mainOrder2));

        assertThat(orderedMenus.countMainCategory()).isEqualTo(7);

    }
}