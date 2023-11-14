package christmas.view;

public enum Message {

    PREFIX("<"),
    POSTFIX(">"),
    START_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    EVENT_PREVIEW_MESSAGE("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER("주문 메뉴"),
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

    public static String priceFormatter(long price) {
        return String.format("%,d원", price);
    }

    public static String menuFormatter(String name, int count) {
        return String.format(name + " " + count + "개");
    }

    public static String resultFormatter(String message) {
        return System.lineSeparator() + PREFIX + message + POSTFIX;
    }

    public String getMessage() {
        return message;
    }
}
