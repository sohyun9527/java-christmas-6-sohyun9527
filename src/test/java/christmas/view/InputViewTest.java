package christmas.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest {

    @DisplayName("스테이크-1,피자-1,파스타-3 올바른 예시 입력 테스트")
    @Test
    void validReadOrders() {

        String input = "스테이크-1,피자-2,파스타-3";
        InputStream fakeInput = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(fakeInput);

        InputView inputView = new InputView();
        List<Map<String, Integer>> validMenus = inputView.readOrders();

        List<Map<String, Integer>> expectedMenus = List.of(
                Map.of("스테이크", 1),
                Map.of("피자", 2),
                Map.of("파스타", 3)
        );

        assertEquals(expectedMenus, validMenus);
        Console.close();
    }

    @DisplayName("스테이크-1,피자-1.파스타-3 잘못된 예시 입력 시 에러 반환")
    @Test
    void invalidReadOrders() {

        String input = "스테이크-1,피자-2.파스타-3";
        InputStream fakeInput = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(fakeInput);

        InputView inputView = new InputView();
        assertThrows(IllegalArgumentException.class, inputView::readOrders);

        Console.close();
    }
}