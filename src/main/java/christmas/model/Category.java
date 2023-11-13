package christmas.model;

public enum Category {
    DRINK("음료"),
    MAIN("메인"),
    DESSERT("디저트"),
    APPETIZER("에피타이저"),
    NULL("없음");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}