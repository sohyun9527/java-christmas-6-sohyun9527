package christmas.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menu menu = (Menu) o;
        return Objects.equals(name, menu.name) && category == menu.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category);
    }
}
