package christmas.model.exception;

import christmas.model.Category;

public class CategoryException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "[ERROR] %s만 주문할 수 없습니다. 다시 입력해 주세요.";

    public CategoryException(Category category) {
        super(String.format(ERROR_MESSAGE, category.getName()));
    }
}
