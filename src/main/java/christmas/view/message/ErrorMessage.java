package christmas.view.message;

public enum ErrorMessage {
    PREFIX("[ERROR] "),
    INVALID_BLANK("아무것도 입력하지 않았습니다."),
    INVALID_NUMBER_TYPE("숫자의 형식이 올바르지 않습니다."),
    INVALID_NAME_COUNT_TYPE("메뉴와 주문 개수는 (-)로 구분해주세요"),
    DUPLICATE_MENU("중복된 메뉴가 존재합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX.message + message;
    }
}
