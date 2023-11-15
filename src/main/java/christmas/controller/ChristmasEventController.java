package christmas.controller;

import christmas.model.Discount;
import christmas.model.EventDate;
import christmas.model.Menu;
import christmas.model.OrderInput;
import christmas.model.OrderedMenu;
import christmas.model.OrderedMenus;
import christmas.model.Orders;
import christmas.model.Promotion;
import christmas.repository.MenuBoard;
import christmas.util.EventDateConverter;
import christmas.util.OrdersConverter;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;
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

    }

    public OrderedMenus generateOrderedMenus(List<Menu> menuBoard) {
        String inputOrder = inputView.getOrders();
        List<String> menus = List.of(inputOrder.split(",", -1));
        List<OrderInput> orderInputs = generateOrderInput(menus);
        List<OrderedMenu> orderedMenus = generateOrderedMenus(orderInputs, menuBoard);

        return new OrderedMenus(orderedMenus);
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

    private void showBenefitResult(Orders orders, Discount discount, EventDate eventDate) {
        List<Long> totalBenefit = discount.result(orders.totalAmount(), eventDate);
        long totalBenefitAmount = totalBenefit.stream().mapToLong(Long::longValue).sum();
        long afterDiscount = orders.totalAmount() + discount.getTotalBenefitAmount();

        outputView.printPromotion(Promotion.promotionEvent(orders.totalAmount()));
        outputView.printBenefitResult(totalBenefit);
        outputView.printDiscountPrice(totalBenefitAmount);
        outputView.printAfterDiscountPrice(afterDiscount);
        outputView.printEventBadge(Promotion.getBadgeByPrice(totalBenefitAmount));
    }

    private void showOrderResult(Orders orders, EventDate eventDate) {
        outputView.printEventPreviewMessage(eventDate.getDate());
        outputView.printOrders(orders.getMenus());
        outputView.printBill(orders.totalAmount());
    }

    private Orders readOrders() {
        return InputView.readUntilValidValue(() -> {
            String orderInput = inputView.getOrders();
            List<Map<String, Integer>> orders = OrdersConverter.convertOrders(orderInput);
            return new Orders(orders);
        });
    }

    private EventDate readVisitDate() {
        return InputView.readUntilValidValue(() -> {
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
}
