package christmas.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuBoardTest {

    @DisplayName("이름과 일치하는 메뉴판 enum 객체를 반환한다")
    @Test
    void getByName() {
        String validMenu = "제로콜라";
        String invalidMenu = "감자탕";

        assertThat(MenuBoard.getByName(validMenu)).isEqualTo(MenuBoard.ZERO_COLA);
        assertThat(MenuBoard.getByName(invalidMenu)).isEqualTo(MenuBoard.NONE);
    }
}