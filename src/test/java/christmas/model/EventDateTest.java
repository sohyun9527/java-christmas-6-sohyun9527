package christmas.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventDateTest {

    @DisplayName("금요일이거나 토요일이라면 true 반환")
    @Test
    public void isWeekendDate() {
        EventDate commonEventDate = new EventDate(13); // 12월 13일 수요일
        EventDate weekendEventDate = new EventDate(15); // 금요일
        assertFalse(commonEventDate.isWeekend());
        assertTrue(weekendEventDate.isWeekend());
    }

    @DisplayName("일요일이거나 25일이라면 true 반환")
    @Test
    public void isStarDate() {
        EventDate startEventDate = new EventDate(24);
        EventDate christmas = new EventDate(25);
        EventDate commonEventDate = new EventDate(12);

        assertTrue(startEventDate.isStarDate());
        assertTrue(christmas.isStarDate());
        assertFalse(commonEventDate.isStarDate());
    }

    @DisplayName("25일 이전이라면 true 반환")
    @Test
    public void isBeforeChristmas() {
        EventDate beforeChristmas = new EventDate(22);
        EventDate afterChristmas = new EventDate(26);

        assertTrue(beforeChristmas.isBeforeChristmas());
        assertFalse(afterChristmas.isBeforeChristmas());
    }
}