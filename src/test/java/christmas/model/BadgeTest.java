package christmas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @DisplayName("2만원 이상이라면 산타를 반환한다")
    @Test
    void getSanta() {
        String badge = Badge.getBadge(20000);

        assertEquals("산타", badge);
    }

    @DisplayName("1만원 이상이라면 트리를 반환한다")
    @Test
    void getTree() {
        String badge = Badge.getBadge(10000);

        assertEquals("트리", badge);
    }

    @DisplayName("5천원 이상이라면 별을 반환한다")
    @Test
    void getStar() {
        String badge = Badge.getBadge(5000);

        assertEquals("별", badge);
    }

    @DisplayName("아무것도 해당하지 않는다면 없음을 반환한다")
    @Test
    void getNone() {
        String badge = Badge.getBadge(4999);

        assertEquals("없음", badge);
    }
}