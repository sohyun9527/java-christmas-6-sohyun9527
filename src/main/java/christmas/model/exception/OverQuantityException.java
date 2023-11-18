package christmas.model.exception;

public class OverQuantityException extends IllegalArgumentException {

    private static final String INVALID_QUANTITY = "[ERROR] %d개 이상 주문할 수 없습니다. 다시 입력해 주세요.";

    public OverQuantityException(int count) {
        super(String.format(INVALID_QUANTITY, count));
    }
}
