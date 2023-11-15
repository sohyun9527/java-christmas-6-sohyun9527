package christmas.model;

import christmas.model.exception.OrderException;
import java.util.List;

public class OrderInput {
    private static final String NAME_QUANTITY_DELIMITER = "-";
    private static final int VALID_SIZE = 2;
    private final String name;
    private final int quantity;

    public OrderInput(String input) {
        List<String> nameAndQuantity = List.of(input.split(NAME_QUANTITY_DELIMITER, -1));
        validateInputMenuSize(nameAndQuantity);
        this.name = nameAndQuantity.get(0);
        this.quantity = convertQuantity(nameAndQuantity.get(1));
    }

    public void validateInputMenuSize(List<String> nameAndQuantity) {
        if (nameAndQuantity.size() != VALID_SIZE) {
            throw new OrderException();
        }
    }

    public int convertQuantity(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new OrderException();
        }
    }

    public OrderedMenu makeOrderedMenu(List<Menu> menuBoard) {
        return menuBoard.stream()
                .filter(menu -> menu.getName().equals(name))
                .findFirst()
                .map(menu -> new OrderedMenu(menu, quantity))
                .orElseThrow(OrderException::new);
    }

}
