package christmas.view;

import christmas.model.Menu;
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

    public void printOrders(List<Menu> orders) {
        System.out.println(Message.titleNameFormatter(Message.ORDER.getMessage()));
        for (Menu menu : orders) {
            System.out.println(Message.menuFormatter(menu.getName(), menu.getCount()));
        }
    }

    // 양수 출력
    public void printBill(long price) {
        System.out.println(resultFormatter("할인 전 총주문 금액"));
        System.out.println(priceFormatter(price));
    }

    // 샴페인 1개 or 없음
    public void printPromotion(String promotionResult) {
        System.out.println(resultFormatter("증정 메뉴"));
        System.out.println(promotionResult);
    }

    // 없음 or 음수 출력
    public void printBenefitResult(List<Long> discountPrice) {
        System.out.println(resultFormatter("혜택 내역"));
        if (discountPrice.isEmpty()) {
            System.out.println("없음");
            return;
        }
        List<String> discountTypes = List.of("크리스마스 디데이 할인:", "평일 할인:", "주말 할인:", "특별 할인:", "증정 이벤트:");
        for (int i = 0; i < discountPrice.size(); i++) {
            long discount = discountPrice.get(i);
            if (discount != 0) {
                String formattedDiscount = priceFormatter(discount);
                String message = String.format("%s %s", discountTypes.get(i), formattedDiscount);
                System.out.println(message);
            }
        }
    }

    // 없음 or 음수
    public void printDiscountPrice(long price) {
        System.out.println(resultFormatter("총혜택 금액"));
        System.out.println(priceFormatter(price));

    }

    // 양수
    public void printAfterDiscountPrice(long price) {
        System.out.println(resultFormatter("할인 후 예상 결제 금액"));
        System.out.println(priceFormatter(price));
    }

    // 없음 or 뱃지
    public void printEventBadge(String badge) {
        System.out.println(resultFormatter("12월 이벤트 배지"));
        System.out.println(badge);
    }

    public String priceFormatter(long price) {
        return String.format("%,d원", price);
    }

    public String resultFormatter(String message) {
        return LINE_SEPARATOR + PREFIX + message + POSTFIX;
    }

}
