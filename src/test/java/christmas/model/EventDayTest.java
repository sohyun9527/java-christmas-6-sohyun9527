package christmas.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.model.exception.DateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EventDayTest {

    @DisplayName("1 ~ 31일 사이가 아니라면 에러 반환")
    @ParameterizedTest
    @ValueSource(ints = {-1, 32, 0})
    public void dateRangeTest(int input) {
        assertThrows(DateException.class, () -> new EventDay(input));
    }

    @DisplayName("금요일이거나 토요일이라면 true 반환")
    @Test
    public void isWeekendDate() {
        EventDay commonEventDay = new EventDay(13); // 12월 13일 수요일
        EventDay weekendEventDay = new EventDay(15); // 금요일
        assertFalse(commonEventDay.isWeekend());
        assertTrue(weekendEventDay.isWeekend());
    }

    @DisplayName("일요일이거나 25일이라면 true 반환")
    @ParameterizedTest
    @ValueSource(ints = {3, 24, 25})
    public void isStarDate(int input) {
        EventDay dates = new EventDay(input);
        EventDay weekDay = new EventDay(13);

        assertTrue(dates.isStarDate());
        assertFalse(weekDay.isStarDate());
    }

    @DisplayName("25일 이전이라면 true 반환")
    @ParameterizedTest
    @ValueSource(ints = {1, 24, 25})
    public void isBeforeChristmas(int input) {
        EventDay beforeChristmas = new EventDay(input);
        EventDay afterChristmas = new EventDay(26);

        assertTrue(beforeChristmas.isBeforeChristmas());
        assertFalse(afterChristmas.isBeforeChristmas());
    }
}