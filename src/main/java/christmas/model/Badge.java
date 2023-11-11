package christmas.model;

import java.util.List;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NONE("없음", 0);


    private final String name;
    private final int price;

    Badge(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static String getBadge(List<Long> discountResult) {
        long discountPrice = discountResult.stream()
                .mapToLong(Long::longValue)
                .sum();

        if (discountPrice >= SANTA.price) {
            return SANTA.name;
        }
        if (discountPrice >= TREE.price) {
            return TREE.name;
        }
        if (discountPrice >= STAR.price) {
            return STAR.name;
        }
        return NONE.name;
    }
}
