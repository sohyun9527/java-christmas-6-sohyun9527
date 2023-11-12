package christmas.model;

import java.util.Arrays;

public enum MenuBoard {
    MUSHROOM_SOUP("양송이수프", 6000, Category.APPETIZER),
    TAPAS("타파스", 5500, Category.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, Category.APPETIZER),
    STEAK("티본스테이크", 55000, Category.MAIN),
    BBQ_RIBS("바비큐립", 54000, Category.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, Category.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, Category.MAIN),
    CHOCO_CAKE("초코케이크", 15000, Category.DESSERT),
    ICE_CREAM("아이스크림", 5000, Category.DESSERT),
    ZERO_COLA("제로콜라", 3000, Category.DRINK),
    RED_WINE("레드와인", 60000, Category.DRINK),
    CHAMPAGNE("샴페인", 25000, Category.DRINK),
    NONE("없음", 0, Category.NULL);

    private final String name;
    private final int price;
    private final Category category;

    MenuBoard(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static MenuBoard getByName(String menu) {
        return Arrays.stream(MenuBoard.values())
                .filter(item -> item.name.equals(menu))
                .findFirst()
                .orElse(NONE);
    }

    public static Category getCategoryByName(String name) {
        return Arrays.stream(MenuBoard.values())
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .map(MenuBoard::getCategory)
                .orElse(Category.NULL);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
}
