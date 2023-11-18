package christmas.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.model.exception.OrderException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderInputTest {

    @DisplayName("메뉴판에 존재하는 이름이라면, Menu, quantity 를 지닌 객체를 반환한다.")
    @Test
    void makeOrderedMenu() {
        List<Menu> menuBoard = List.of(
                new Menu("감자탕", 6000, Category.MAIN),
                new Menu("소고기", 9999, Category.MAIN)
        );
        OrderInput validOrderInput = new OrderInput("감자탕-1");
        OrderInput invalidOrderInput = new OrderInput("김치찌개-3");

        assertThatThrownBy(() -> invalidOrderInput.makeOrderedMenu(menuBoard))
                .isInstanceOf(OrderException.class);

        OrderedMenu orderedMenu = validOrderInput.makeOrderedMenu(menuBoard);
        assertThat(orderedMenu.getName()).isEqualTo("감자탕");
        assertThat(orderedMenu.getQuantity()).isEqualTo(1);
    }

    @DisplayName("하나의 메뉴에 대한 이름, 개수 구분 기능 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"감자탕,1", "김치찌개=3", "감자탕2", "김치찌개-a"})
    void orderInputTest(String input) {

        assertThatThrownBy(() -> new OrderInput(input))
                .isInstanceOf(OrderException.class);
    }
}