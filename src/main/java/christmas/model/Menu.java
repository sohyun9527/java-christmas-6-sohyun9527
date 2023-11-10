package christmas.model;

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
            throw new IllegalArgumentException("최소 1개 이상 주문해야합니다.");
        }
    }

    private void validateContainMenu(String name) {
        if (MenuBoard.getByName(name) == null) {
            throw new IllegalArgumentException(name + "은 존재하지 않는 메뉴입니다.");
        }
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
