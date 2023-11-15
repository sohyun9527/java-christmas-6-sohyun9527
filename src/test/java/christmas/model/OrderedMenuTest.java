package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.model.exception.OrderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class OrderedMenuTest {

    Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu("감자탕", 9000, Category.MAIN);
    }

    @DisplayName("수량이 1개 미만이라면 예외를 반환한다")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -32})
    public void quantityValidationTest(int input) {

        assertThatThrownBy(() -> new OrderedMenu(menu, input))
                .isInstanceOf(OrderException.class);
    }

    @DisplayName("주문된 메뉴의 가격 반환 기능")
    @ParameterizedTest
    @CsvSource({
            "3, 27000",
            "5, 45000",
            "7, 63000"
    })
    public void menuPriceTest(int quantity, long expectedPrice) {

        OrderedMenu orderedMenu = new OrderedMenu(menu, quantity);

        assertThat(orderedMenu.calculateMenuPrice()).isEqualTo(expectedPrice);
    }
}