package christmas.model;

public enum Promotion {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NONE("없음", 0);


    private final String name;
    private final int price;

    Promotion(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static String getBadge(int price) {
        if (price >= SANTA.price) {
            return SANTA.name;
        }
        if (price >= TREE.price) {
            return TREE.name;
        }
        if (price >= STAR.price) {
            return STAR.name;
        }
        return NONE.name;
    }
}
