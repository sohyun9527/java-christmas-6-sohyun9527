package christmas.view.message;

public enum ErrorMessage {
    PREFIX("[ERROR] "),
    INVALID_BLANK("아무것도 입력하지 않았습니다."),
    INVALID_DATE_RANGE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    OVER_QUANTITY_ORDER("20개를 초과하여 주문할 수 없습니다."),
    CANT_ONLY_DRINKS_ORDER("음료만 주문할 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX.message + message;
    }
}
