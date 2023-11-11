package christmas.model;

import static christmas.view.message.ErrorMessage.INVALID_ORDER;

public class Menu {
    private static final int MINIMUM_COUNT = 1;
    private final String name;
    private final int count;

    public Menu(String name, int count) {
        validateContainMenu(name);
        this.name = name;
        validateOrderCount(count);
        this.count = count;
    }

    private void validateOrderCount(int count) {
        if (count < MINIMUM_COUNT) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private void validateContainMenu(String name) {
        if (MenuBoard.getByName(name) == MenuBoard.NONE) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
