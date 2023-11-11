package christmas.view;

import static christmas.view.message.Message.BADGE;
import static christmas.view.message.Message.BEFORE_DISCOUNT;
import static christmas.view.message.Message.COUNT;
import static christmas.view.message.Message.DISCOUNT_PAYMENT;
import static christmas.view.message.Message.DISCOUNT_PRICE;
import static christmas.view.message.Message.DISCOUNT_RESULT;
import static christmas.view.message.Message.NONE;
import static christmas.view.message.Message.ORDER;
import static christmas.view.message.Message.PROMOTION;
import static christmas.view.message.Message.REQUEST_MENU_COUNT;
import static christmas.view.message.Message.REQUEST_VISIT_DAY;
import static christmas.view.message.Message.SHOW_EVENT_MESSAGE;
import static christmas.view.message.Message.START_MESSAGE;

import christmas.model.Menu;
import christmas.model.MenuBoard;
import java.text.DecimalFormat;
import java.util.List;

public class OutputView {
    private static final String PREFIX = "<";
    private static final String POSTFIX = ">";

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printNewLine() {
        System.out.println();
    }

    public void printStartMessage() {
        System.out.println(START_MESSAGE.getMessage());
    }

    public void printRequestVisitDay() {
        System.out.println(REQUEST_VISIT_DAY.getMessage());
    }

    public void printRequestOrder() {
        System.out.println(REQUEST_MENU_COUNT.getMessage());
    }

    public void printEventPreview(int day) {
        String message = SHOW_EVENT_MESSAGE.getMessage();
        String result = String.format(message, day);
        System.out.println(result);
    }

    public void printOrders(List<Menu> orders) {
        System.out.println("\n" + PREFIX + ORDER.getMessage() + POSTFIX);
        for (Menu menu : orders) {
            System.out.println(menu.getName() + " " + menu.getCount() + COUNT.getMessage());
        }
    }

    public void printBill(long price) {
        System.out.println(resultFormatter(BEFORE_DISCOUNT.getMessage()));
        System.out.println(priceFormatter(price));
    }

    public void printPromotion(MenuBoard menu) {
        System.out.println(resultFormatter(PROMOTION.getMessage()));
        System.out.println(menu.getName());
    }

    public String priceFormatter(long price) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###원");
        return decimalFormat.format(price);
    }

    public String resultFormatter(String message) {
        return "\n" + PREFIX + message + POSTFIX;
    }

    public void printDiscountResult(List<Long> discountPrice) {
        System.out.println(resultFormatter(DISCOUNT_RESULT.getMessage()));
        if (discountPrice.isEmpty()) {
            System.out.println(NONE.getMessage());
            return;
        }
        List<String> discountTypes = List.of("크리스마스 할인:", "평일 할인:", "주말 할인:", "특별 할인:", "증정 이벤트:");

        for (int i = 0; i < discountPrice.size(); i++) {
            long discount = discountPrice.get(i);
            if (discount != 0) {
                String formattedDiscount = priceFormatter(discount);
                String message = String.format("%s -%s", discountTypes.get(i), formattedDiscount);
                System.out.println(message);
            }
        }
    }

    public void printDiscountPrice(List<Long> result) {
        System.out.println(resultFormatter(DISCOUNT_PRICE.getMessage()));
        long price = result.stream().mapToLong(Long::longValue).sum();
        System.out.println(priceFormatter(price));

    }

    public void printAfterDiscountPrice(long price) {
        System.out.println(resultFormatter(DISCOUNT_PAYMENT.getMessage()));
        System.out.println(priceFormatter(price));
    }

    public void printEventBadge(String badge) {
        System.out.println(resultFormatter(BADGE.getMessage()));
        System.out.println(badge);
    }

}
