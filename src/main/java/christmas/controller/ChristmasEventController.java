package christmas.controller;

import christmas.model.Benefit;
import christmas.model.DiscountType;
import christmas.model.EventDay;
import christmas.model.Menu;
import christmas.model.OrderInput;
import christmas.model.OrderedMenu;
import christmas.model.OrderedMenus;
import christmas.model.Promotion;
import christmas.repository.MenuBoard;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ChristmasEventController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Benefit benefit = new Benefit();

    public ChristmasEventController(InputView inputView,
                                    OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<Menu> menuBoard = makeMenuBoard();
        outputView.printStartMessage();
        EventDay eventDay = readVisitDate();
        OrderedMenus orderedMenus = generateOrderedMenus(menuBoard);

        EnumMap<DiscountType, Long> result = benefit.result(eventDay, orderedMenus);
        showOrderResult(eventDay, orderedMenus);
        showBenefitDetails(result);
        showBenefitResult(orderedMenus);
    }

    public void showBenefitResult(OrderedMenus orderedMenus) {
        long totalBenefitAmount = benefit.getTotalBenefitAmount();
        long finalPrice = orderedMenus.totalAmount() + totalBenefitAmount;

        outputView.printBenefitAmount(totalBenefitAmount);
        outputView.printAfterDiscountPrice(finalPrice);
        outputView.printEventBadge(Promotion.getBadgeByPrice(totalBenefitAmount));
    }

    public void showOrderResult(EventDay eventDay, OrderedMenus menus) {
        long orderTotalAmount = menus.totalAmount();

        outputView.printEventPreviewMessage(eventDay.getDay());
        outputView.printOrderedMenus(menus.getOrderedMenus());
        outputView.printBeforeDiscountAmount(orderTotalAmount);
        outputView.printPromotionResult(Promotion.promotionEvent(orderTotalAmount));
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

    public void showBenefitDetails(EnumMap<DiscountType, Long> benefitResult) {
        outputView.printBenefitTitle();
        long totalBenefitPrice = benefit.totalBenefitPrice(benefitResult);

        if (totalBenefitPrice == 0) {
            outputView.printNoneMessage();
            return;
        }
        for (DiscountType type : DiscountType.values()) {
            long value = benefitResult.get(type);
            if (value != 0) {
                outputView.printBenefitDetail(type.getMessage(), value);
            }
        }
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

    private EventDay readVisitDate() {
        return readUntilValidValue(() -> {
            String dateInput = inputView.getVisitDate();
            return new EventDay(dateInput);
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
