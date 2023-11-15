package christmas.view;

public enum Message {

    VISIT_DATE_MESSAGE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    REQUEST_ORDER_MESSAGE("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    
    START_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    EVENT_PREVIEW_MESSAGE("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER("주문 메뉴"),
    NONE("없음"),
    TOTAL_ORDER_AMOUNT("할인 전 총주문 금액"),
    PROMOTION("증정 메뉴"),
    DISCOUNT("혜택 내역"),
    DISCOUNT_AMOUNT("총혜택 금액"),
    AFTER_DISCOUNT_AMOUNT("할인 후 예상 결제 금액"),
    BADGE("12월 이벤트 배지");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
