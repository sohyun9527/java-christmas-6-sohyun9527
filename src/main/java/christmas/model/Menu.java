package christmas.model;

public class Menu {
    private final String name;
    private final long price;
    private final Category category;

    public Menu(String name, long price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public boolean isDrinkCategory() {
        return this.category == Category.DRINK;
    }

    public boolean isDessertCategory() {
        return this.category == Category.DESSERT;
    }

    public boolean isMainCategory() {
        return this.category == Category.MAIN;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }
}
