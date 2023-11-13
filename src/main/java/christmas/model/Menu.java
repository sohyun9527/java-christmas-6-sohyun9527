package christmas.model;

import christmas.model.exception.OrderException;

public class Menu {
    private static final int MINIMUM_COUNT = 1;
    private final String name;
    private final int count;
    private final Category category;

    public Menu(String name, int count) {
        validateContainMenu(name);
        this.name = name;
        validateOrderCount(count);
        this.count = count;
        this.category = MenuBoard.getCategoryByName(name);
    }

    private void validateOrderCount(int count) {
        if (count < MINIMUM_COUNT) {
            throw new OrderException();
        }
    }

    private void validateContainMenu(String name) {
        if (MenuBoard.getByName(name) == MenuBoard.NONE) {
            throw new OrderException();
        }
    }

    public boolean isDessertCategory() {
        return this.category.equals(Category.DESSERT);
    }

    public boolean isMainCategory() {
        return this.category.equals(Category.MAIN);
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
