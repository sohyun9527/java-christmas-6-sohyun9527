package christmas.controller;

import christmas.model.EventDate;
import christmas.model.Menu;
import christmas.model.OrderInput;
import christmas.model.OrderedMenu;
import christmas.model.OrderedMenus;
import christmas.repository.MenuBoard;
import christmas.util.EventDateConverter;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ChristmasEventController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasEventController(InputView inputView,
                                    OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<Menu> menuBoard = makeMenuBoard();
        outputView.printStartMessage();
        EventDate eventDate = readVisitDate();
        OrderedMenus menus = generateOrderedMenus(menuBoard);

    }

    public OrderedMenus generateOrderedMenus(List<Menu> menuBoard) {
        return readUntilValidValue(() -> {
            String inputOrder = inputView.getOrders();
            List<String> menus = List.of(inputOrder.split(",", -1));
            List<OrderInput> orderInputs = generateOrderInput(menus);
            List<OrderedMenu> orderedMenus = generateOrderedMenus(orderInputs, menuBoard);

            return new OrderedMenus(orderedMenus);
        });
    }

    public List<OrderedMenu> generateOrderedMenus(List<OrderInput> orderInputs, List<Menu> menuBoard) {
        return orderInputs.stream()
                .map(input -> input.makeOrderedMenu(menuBoard))
                .toList();
    }

    public List<OrderInput> generateOrderInput(List<String> inputs) {
        return inputs.stream()
                .map(OrderInput::new)
                .toList();
    }


    private EventDate readVisitDate() {
        return readUntilValidValue(() -> {
            String dateInput = inputView.getVisitDate();
            int date = EventDateConverter.convertDate(dateInput);
            return new EventDate(date);
        });
    }

    private List<Menu> makeMenuBoard() {
        return Stream.of(MenuBoard.values())
                .map(menuBoard -> new Menu(menuBoard.getName(), menuBoard.getPrice(), menuBoard.getCategory()))
                .toList();
    }

    public <T> T readUntilValidValue(Supplier<T> inputFunction) {
        while (true) {
            try {
                return inputFunction.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
