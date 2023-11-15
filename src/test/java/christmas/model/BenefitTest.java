package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitTest {

    private Benefit benefit;
    private long totalAmount;

    @BeforeEach
    void setUp() {
        List<Menu> orders = List.of(
                new Menu("초코케이크", 3),
                new Menu("티본스테이크", 2),
                new Menu("시저샐러드", 3),
                new Menu("아이스크림", 2),
                new Menu("바비큐립", 3));
        benefit = new Benefit(orders);
        totalAmount = 351_000;
    }

    @DisplayName("주문 금액이 10,000원을 넘지 못한다면 모든 이벤트를 적용하지 않는다")
    @Test
    void lessMinimumPrice() {
        long price = 9999;
        EventDay eventDay = new EventDay(15);
        List<Long> result = benefit.result(price, eventDay);

        assertTrue(result.isEmpty());
    }

    @DisplayName("평일이라면 디저트 메뉴를 2,023원씩 할인받는다")
    @Test
    void weekdayDiscount() {
        EventDay eventDay = new EventDay(26); // 화요일
        List<Long> result = benefit.result(totalAmount, eventDay);

        assertThat(result.get(1)).isEqualTo(-((3 + 2) * 2023));
    }

    @DisplayName("주말이라면 메인 메뉴를 2,023원씩 할인받는다")
    @Test
    void weekendDiscount() {
        EventDay eventDay = new EventDay(29); // 금요일
        List<Long> result = benefit.result(totalAmount, eventDay);

        assertThat(result.get(2)).isEqualTo(-((2 + 3) * 2023));
    }

    @DisplayName("크리스마스 할인과 주말할인 동시 적용 테스트")
    @Test
    void christmasAndWeekendDiscount() {
        EventDay eventDay = new EventDay(15);
        List<Long> result = benefit.result(totalAmount, eventDay);

        assertThat(result.get(0)).isEqualTo(-2400);
        assertThat(result.get(2)).isEqualTo(-((2 + 3) * 2023));
    }

    @DisplayName("크리스마스, 평일, 특별 할인 동시 적용 테스트")
    @Test
    void christmasWeekdayStartDiscount() {
        EventDay eventDay = new EventDay(25);
        List<Long> result = benefit.result(totalAmount, eventDay);

        assertThat(result.get(0)).isEqualTo(-3400);
        assertThat(result.get(1)).isEqualTo(-((2 + 3) * 2023));
        assertThat(result.get(3)).isEqualTo(-1000);
    }

    @DisplayName("총 혜택 금액은 샴페인 가격을 포함한다")
    @Test
    void resultContainChampagnePrice() {
        EventDay eventDay = new EventDay(25);
        List<Long> result = benefit.result(totalAmount, eventDay);
        long totalAmount = result.stream().mapToLong(Long::longValue).sum(); // 샹페인 가격 포함
        long totalBenefitAmount = benefit.getTotalBenefitAmount(); // 혜택 가격은 샴페인 가격 미포함

        assertThat(totalAmount - totalBenefitAmount).isEqualTo(-25000);
    }
}