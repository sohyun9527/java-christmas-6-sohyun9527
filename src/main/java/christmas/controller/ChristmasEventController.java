package christmas.controller;

import christmas.model.Badge;
import christmas.model.Discount;
import christmas.model.EventDate;
import christmas.model.Orders;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;

public class ChristmasEventController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasEventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        EventDate eventDate = getVisitDate();
        Orders orders = getOrders();
        showOrderResult(orders, eventDate);
        Discount discount = new Discount(orders.getMenus());
        showBenefitResult(orders, discount, eventDate);
    }

    private void showBenefitResult(Orders orders, Discount discount, EventDate eventDate) {
        List<Long> totalBenefit = discount.result(orders.totalAmount(), eventDate);
        long totalBenefitAmount = totalBenefit.stream().mapToLong(Long::longValue).sum();
        long afterDiscount = orders.totalAmount() - discount.getTotalBenefitAmount();

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

    public void showDiscountResult(Discount discount, Orders orders, EventDate eventDate) {
        List<Long> totalBenefit = discount.result(orders.totalAmount(), eventDate);
        long totalBenefitAmount = totalBenefit.stream().mapToLong(Long::longValue).sum();

        outputView.printBenefitResult(totalBenefit);
        outputView.printDiscountPrice(totalBenefitAmount);
    }


    private Orders getOrders() {
        return InputView.readUntilValidValue(() -> {
            List<Map<String, Integer>> input = inputView.readOrders();
            return new Orders(input);

        });
    }

    private EventDate getVisitDate() {
        return InputView.readUntilValidValue(() -> {
            int visitDate = inputView.readVisitDay();
            return new EventDate(visitDate);
        });
    }


}
