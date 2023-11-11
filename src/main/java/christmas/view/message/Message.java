package christmas.view.message;

public enum Message {
    START_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    REQUEST_VISIT_DAY("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    REQUEST_MENU_COUNT("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    SHOW_EVENT_MESSAGE("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER("주문 메뉴"),
    COUNT("개"),
    NONE("없음"),
    BEFORE_DISCOUNT("할인 전 총주문 금액"),
    PROMOTION("증정 메뉴"),
    DISCOUNT_RESULT("혜택 내역"),
    DISCOUNT_PRICE("총혜택 금액"),
    DISCOUNT_PAYMENT("할인 후 예상 결제 금액"),
    BADGE("12월 이벤트 배지");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
