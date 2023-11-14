package christmas.view;

import christmas.model.Menu;
import java.text.DecimalFormat;
import java.util.List;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String PREFIX = "<";
    private static final String POSTFIX = ">";

    public void printStartMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventPreviewMessage(int day) {
        String message = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
        String result = String.format(message, day);
        System.out.println(result);
    }

    public void printOrders(List<Menu> orders) {
        System.out.println(resultFormatter("주문 메뉴"));
        for (Menu menu : orders) {
            System.out.println(menu.getName() + " " + menu.getCount() + "개");
        }
    }

    public void printBill(long price) {
        System.out.println(resultFormatter("할인 전 총주문 금액"));
        System.out.println(priceFormatter(price));
    }

    public void printPromotion(String promotionResult) {
        System.out.println(resultFormatter("증정 메뉴"));
        System.out.println(promotionResult);
    }

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
                String message = String.format("%s -%s", discountTypes.get(i), formattedDiscount);
                System.out.println(message);
            }
        }
    }

    public void printDiscountPrice(long price) {
        System.out.println(resultFormatter("총혜택 금액"));
        if (price == 0) {
            System.out.println("0원");
            return;
        }
        System.out.println("-" + priceFormatter(price));

    }

    public void printAfterDiscountPrice(long price) {
        System.out.println(resultFormatter("할인 후 예상 결제 금액"));
        System.out.println(priceFormatter(price));
    }

    public void printEventBadge(String badge) {
        System.out.println(resultFormatter("12월 이벤트 배지"));
        System.out.println(badge);
    }

    public String priceFormatter(long price) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###원");
        return decimalFormat.format(price);
    }

    public String resultFormatter(String message) {
        return LINE_SEPARATOR + PREFIX + message + POSTFIX;
    }

    public void printSubject(String message) {
        System.out.println(LINE_SEPARATOR + PREFIX + message + POSTFIX);
    }

}
