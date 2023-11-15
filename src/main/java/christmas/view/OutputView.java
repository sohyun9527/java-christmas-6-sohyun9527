package christmas.view;

import christmas.model.OrderedMenu;
import java.util.List;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String PREFIX = "<";
    private static final String POSTFIX = ">";

    public void printStartMessage() {
        System.out.println(Message.START_MESSAGE.getMessage());
    }

    public void printEventPreviewMessage(int day) {
        String previewMessage = String.format(Message.EVENT_PREVIEW_MESSAGE.getMessage(), day);
        System.out.println(previewMessage);
    }

    public void printOrderedMenus(List<OrderedMenu> menus) {
        System.out.println(titleFormatter(Message.ORDER.getMessage()));
        for (OrderedMenu menu : menus) {
            System.out.println(menuFormatter(menu.getName(), menu.getQuantity()));
        }
    }


    // 양수 출력
    public void printBeforeDiscountAmount(long price) {
        System.out.println(titleFormatter(Message.TOTAL_ORDER_AMOUNT.getMessage()));
        System.out.println(priceFormatter(price));
    }

    // 샴페인 1개 or 없음
    public void printPromotionResult(String promotionResult) {
        System.out.println(titleFormatter(Message.PROMOTION.getMessage()));
        System.out.println(promotionResult);
    }

    public void printBenefitTitle() {
        System.out.println(titleFormatter(Message.DISCOUNT.getMessage()));
    }

    public void printNoneMessage() {
        System.out.println(Message.NONE.getMessage());
    }

    public void printBenefitDetail(String discountType, long price) {
        System.out.println(discountType + ": " + priceFormatter(price));
    }

    // 없음 or 음수
    public void printBenefitAmount(long price) {
        System.out.println(titleFormatter(Message.DISCOUNT_AMOUNT.getMessage()));
        System.out.println(priceFormatter(price));
    }

    // 양수
    public void printAfterDiscountPrice(long price) {
        System.out.println(titleFormatter(Message.AFTER_DISCOUNT_AMOUNT.getMessage()));
        System.out.println(priceFormatter(price));
    }

    // 없음 or 뱃지
    public void printEventBadge(String badge) {
        System.out.println(titleFormatter(Message.BADGE.getMessage()));
        System.out.println(badge);
    }

    public String menuFormatter(String name, int quantity) {
        return name + " " + quantity + "개";
    }

    public String priceFormatter(long price) {
        return String.format("%,d원", price);
    }

    public String titleFormatter(String message) {
        return LINE_SEPARATOR + PREFIX + message + POSTFIX;
    }

}
