package christmas.model.exception;

public class OrderException extends IllegalArgumentException {
    private static final String INVALID_ORDER = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public OrderException() {
        super(INVALID_ORDER);
    }
}
