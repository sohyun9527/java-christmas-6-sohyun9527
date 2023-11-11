package christmas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @DisplayName("2만원 이상이라면 산타를 반환한다")
    @Test
    void getSanta() {
        List<Long> price = List.of(1000L, 5000L, 10000L, 4000L);
        String badge = Badge.getBadge(price);

        assertEquals("산타", badge);
    }

    @DisplayName("1만원 이상이라면 트리를 반환한다")
    @Test
    void getTree() {
        List<Long> price = List.of(1000L, 5000L, 4000L);
        String badge = Badge.getBadge(price);

        assertEquals("트리", badge);
    }

    @DisplayName("5천원 이상이라면 별을 반환한다")
    @Test
    void getStar() {
        List<Long> price = List.of(5000L, 4000L);
        String badge = Badge.getBadge(price);

        assertEquals("별", badge);
    }

    @DisplayName("아무것도 해당하지 않는다면 없음을 반환한다")
    @Test
    void getNone() {
        List<Long> price = List.of(4000L);
        String badge = Badge.getBadge(price);

        assertEquals("없음", badge);
    }
}