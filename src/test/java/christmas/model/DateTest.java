package christmas.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateTest {

    @DisplayName("일 ~ 목 사이의 날짜라면 true 반환")
    @Test
    public void isCommonDate() {
        Date commonDate = new Date(13); // 12월 13일 수요일
        Date weekendDate = new Date(15); // 금요일

        assertTrue(commonDate.isCommonDate());
        assertFalse(weekendDate.isCommonDate());
    }

    @DisplayName("금요일이거나 토요일이라면 true 반환")
    @Test
    public void isWeekendDate() {
        Date commonDate = new Date(13); // 12월 13일 수요일
        Date weekendDate = new Date(15); // 금요일
        assertFalse(commonDate.isWeekend());
        assertTrue(weekendDate.isWeekend());
    }

    @DisplayName("일요일이거나 25일이라면 true 반환")
    @Test
    public void isStarDate() {
        Date startDate = new Date(24);
        Date christmas = new Date(25);
        Date commonDate = new Date(12);

        assertTrue(startDate.isStarDate());
        assertTrue(christmas.isStarDate());
        assertFalse(commonDate.isStarDate());
    }

    @DisplayName("25일 이전이라면 true 반환")
    @Test
    public void isBeforeChristmas() {
        Date beforeChristmas = new Date(22);
        Date afterChristmas = new Date(26);

        assertTrue(beforeChristmas.isBeforeChristmas());
        assertFalse(afterChristmas.isBeforeChristmas());
    }
}