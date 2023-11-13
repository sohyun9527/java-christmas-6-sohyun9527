package christmas.controller;

import christmas.model.Badge;
import christmas.model.Discount;
import christmas.model.EventDate;
import christmas.model.Orders;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ChristmasEventController {
    private static final int EVENT_MINIMUM_PRICE = 10_000;
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void run() {
        outputView.printStartMessage();
        EventDate eventDate = getVisitDate();
        Orders orders = getOrders();
        showOrderResult(orders, eventDate);

        Discount discount = new Discount(orders.getMenus());
        outputView.printPromotion(discount.champagnePromotion(orders.totalAmount()));

        // 증정 가격 포함
        List<Long> discountResult = discount.result(orders.totalAmount(), eventDate);
        long totalDiscount = discountResult.stream().mapToLong(Long::longValue).sum();

        outputView.printDiscountResult(discountResult);
        outputView.printDiscountPrice(totalDiscount);

        long afterDiscount = orders.totalAmount() - discount.getTotalBenefitAmount();
        outputView.printAfterDiscountPrice(afterDiscount);
        outputView.printEventBadge(Badge.getBadge(totalDiscount));
    }

    private void showOrderResult(Orders orders, EventDate eventDate) {
        if (orders.totalAmount() <= EVENT_MINIMUM_PRICE) {
            if (inputView.readReorder()) {
                getOrders();
            }
        }
        outputView.printEventPreview(eventDate.getDate());
        outputView.printOrders(orders.getMenus());
        outputView.printBill(orders.totalAmount());
    }

    public void showDiscountResult(Discount discount, Orders orders, EventDate eventDate) {
        List<Long> totalBenefit = discount.result(orders.totalAmount(), eventDate);
        long totalBenefitAmount = totalBenefit.stream().mapToLong(Long::longValue).sum();

        outputView.printDiscountResult(totalBenefit);
        outputView.printDiscountPrice(totalBenefitAmount);
    }


    private Orders getOrders() {
        return readUntilValidValue(() -> {
            List<Map<String, Integer>> input = inputView.readOrders();
            return new Orders(input);

        });
    }

    private EventDate getVisitDate() {
        return readUntilValidValue(() -> {
            int visitDate = inputView.readVisitDay();
            return new EventDate(visitDate);
        });
    }

    private <T> T readUntilValidValue(Supplier<T> inputFunction) {
        while (true) {
            try {
                return inputFunction.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
