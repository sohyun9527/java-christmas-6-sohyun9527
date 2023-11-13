package christmas.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
    @ParameterizedTest
    @ValueSource(ints = {3, 24, 25})
    public void isStarDate(int input) {
        EventDate dates = new EventDate(input);
        EventDate weekDay = new EventDate(13);

        assertTrue(dates.isStarDate());
        assertFalse(weekDay.isStarDate());
    }

    @DisplayName("25일 이전이라면 true 반환")
    @ParameterizedTest
    @ValueSource(ints = {1, 24, 25})
    public void isBeforeChristmas(int input) {
        EventDate beforeChristmas = new EventDate(input);
        EventDate afterChristmas = new EventDate(26);

        assertTrue(beforeChristmas.isBeforeChristmas());
        assertFalse(afterChristmas.isBeforeChristmas());
    }
}