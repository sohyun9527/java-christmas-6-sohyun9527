package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitTest {
    Menu porkMenu;
    Menu duplicateDrink;
    Menu stake;
    Menu drink;
    Menu chicken;
    Menu drink1;
    Menu drink2;
    Menu dessert;
    OrderedMenu mainOrder;
    OrderedMenu drinkOrder;
    OrderedMenu dessertOrder;
    Benefit benefit = new Benefit();

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

        mainOrder = new OrderedMenu(porkMenu, 5);
        drinkOrder = new OrderedMenu(drink, 2);
        dessertOrder = new OrderedMenu(dessert, 10);
    }

    @DisplayName("15일 할인 - 크리스마스, 주말")
    @Test
    void christmasAndWeekDay() {
        OrderedMenus orderedMenus = new OrderedMenus(List.of(mainOrder, dessertOrder, drinkOrder));
        EventDay eventDay = new EventDay(15);
        EnumMap<DiscountType, Long> result = benefit.result(eventDay, orderedMenus);

        assertThat(result.get(DiscountType.WEEKEND)).isEqualTo(-(2023 * 5));
        assertThat(result.get(DiscountType.CHRISTMAS)).isEqualTo(-(2400));
    }

    @DisplayName("25일 할인 - 크리스마스, 평일, 특별")
    @Test
    void christmasAndWeekdayAndSpecial() {
        OrderedMenus orderedMenus = new OrderedMenus(List.of(mainOrder, dessertOrder, drinkOrder));
        EventDay eventDay = new EventDay(25);
        EnumMap<DiscountType, Long> result = benefit.result(eventDay, orderedMenus);

        assertThat(result.get(DiscountType.WEEKEND)).isEqualTo(0);
        assertThat(result.get(DiscountType.WEEKDAY)).isEqualTo(-(10 * 2023));
        assertThat(result.get(DiscountType.CHRISTMAS)).isEqualTo(-(3400));
    }
}