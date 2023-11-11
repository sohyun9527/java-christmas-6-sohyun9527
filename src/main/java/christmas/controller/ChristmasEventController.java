package christmas.controller;

import christmas.model.Date;
import christmas.model.Discount;
import christmas.model.MenuBoard;
import christmas.model.Orders;
import christmas.model.Promotion;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ChristmasEventController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void run() {

        Date date = getVisitDate();
        outputView.printRequestOrder();
        List<Map<String, Integer>> order = inputView.readOrders();
        Orders orders = new Orders(order);
        outputView.printEventPreview(date.getDate());
        outputView.printOrders(orders.getMenus());
        outputView.printBill(orders.getTotalPrice());
        outputView.printPromotion(MenuBoard.getChampagne(orders.getTotalPrice()));
        Discount discount = new Discount(orders.makeBill());
        List<Long> discountResult = discount.discountResult(orders.getTotalPrice(), date);
        outputView.printDiscountResult(discountResult);
        outputView.printDiscountPrice(discountResult);
        long afterDiscount = orders.getTotalPrice() - discount.getTotalDiscount();
        outputView.printAfterDiscountPrice(afterDiscount);
        outputView.printEventBadge(Promotion.getBadge(discountResult));
    }

    private Date getVisitDate() {
        return untilValidReadValue(() -> {
            outputView.printStartMessage();
            outputView.printRequestVisitDay();
            int visitDate = inputView.readVisitDay();
            return new Date(visitDate);
        });
    }

    private <T> T untilValidReadValue(Supplier<T> inputFunction) {
        while (true) {
            try {
                return inputFunction.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
