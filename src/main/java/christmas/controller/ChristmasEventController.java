package christmas.controller;

import christmas.model.Date;
import christmas.model.Discount;
import christmas.model.MenuBoard;
import christmas.model.Orders;
import christmas.model.Promotion;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class ChristmasEventController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void run() {
        outputView.printStartMessage();
        Date date = getVisitDate();
        Orders orders = getOrders();
        showOrderResult(orders, date);
        showPromotionResult(orders, date);
    }

    private void showPromotionResult(Orders orders, Date date) {
        outputView.printPromotion(MenuBoard.getChampagne(orders.beforeSalePrice()));
        Discount discount = new Discount(orders.makeBill());
        List<Long> discountResult = discount.result(orders.beforeSalePrice(), date);
        outputView.printDiscountResult(discountResult);
        outputView.printDiscountPrice(discountResult);
        long afterDiscount = orders.beforeSalePrice() - discount.getTotalDiscount();
        outputView.printAfterDiscountPrice(afterDiscount);
        outputView.printEventBadge(Promotion.getBadge(discountResult));
    }

    private void showOrderResult(Orders orders, Date date) {
        outputView.printEventPreview(date.getDate());
        outputView.printOrders(orders.getMenus());
        outputView.printBill(orders.beforeSalePrice());
    }

    private Orders getOrders() {
        return untilValidReadValue(() -> {
            outputView.printRequestOrder();
            return new Orders(inputView.readOrders());
        });
    }

    private Date getVisitDate() {
        return untilValidReadValue(() -> {
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
