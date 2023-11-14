package christmas.controller;

import christmas.model.Badge;
import christmas.model.Discount;
import christmas.model.EventDate;
import christmas.model.Orders;
import christmas.util.EventDateConverter;
import christmas.util.OrdersConverter;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;

public class ChristmasEventController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasEventController(InputView inputView,
                                    OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        EventDate eventDate = readVisitDate();
        Orders orders = readOrders();
        showOrderResult(orders, eventDate);
        Discount discount = new Discount(orders.getMenus());
        showBenefitResult(orders, discount, eventDate);
    }

    private void showBenefitResult(Orders orders, Discount discount, EventDate eventDate) {
        List<Long> totalBenefit = discount.result(orders.totalAmount(), eventDate);
        long totalBenefitAmount = totalBenefit.stream().mapToLong(Long::longValue).sum();
        long afterDiscount = orders.totalAmount() + discount.getTotalBenefitAmount();

        outputView.printPromotion(discount.champagnePromotion(orders.totalAmount()));
        outputView.printBenefitResult(totalBenefit);
        outputView.printDiscountPrice(totalBenefitAmount);
        outputView.printAfterDiscountPrice(afterDiscount);
        outputView.printEventBadge(Badge.getBadge(totalBenefitAmount));
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
}
