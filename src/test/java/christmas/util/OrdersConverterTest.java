package christmas.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.model.exception.OrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrdersConverterTest {

    @DisplayName("지정된 구분자로 나누지 않았을 경우 OrderException 을 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"감자칩-1,빼빼로-1.치즈볼-2", "감자칩=3,샐러드-2,치킨-3", "감자칩-3,빼빼로-2,치즈볼--2", "감자칩--2", "감자칩-2,", "감자칩-",
            "감자칩02", "감자칩- 2, 빼빼로-3", "감자칩-,빼빼로-3,"})
    void convertOrders(String input) {

        assertThrows(OrderException.class, () -> OrdersConverter.convertOrders(input));
    }

    @DisplayName("올바른 구분자 입력 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"감자칩-1,빼빼로-2,치즈볼-3", "치즈볼-99,감자칩-2,양념감자-1"})
    void validOrders(String input) {
        assertDoesNotThrow(() -> OrdersConverter.convertOrders(input));
    }
}