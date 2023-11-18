package christmas.model;

public enum Promotion {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    CHAMPAGNE("샴페인 1개", 25000),
    NONE("없음", 0);

    private static final long PROMOTION_PRICE = 120_000;
    private final String name;
    private final int price;

    Promotion(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static String promotionEvent(long price) {
        if (price >= PROMOTION_PRICE) {
            return CHAMPAGNE.name;
        }
        return NONE.name;
    }

    public static String getBadgeByPrice(long discountPrice) {
        long absPrice = Math.abs(discountPrice);
        if (absPrice >= SANTA.price) {
            return SANTA.name;
        }
        if (absPrice >= TREE.price) {
            return TREE.name;
        }
        if (absPrice >= STAR.price) {
            return STAR.name;
        }
        return NONE.name;
    }
}
