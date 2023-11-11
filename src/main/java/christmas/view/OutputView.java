package christmas.view;

import static christmas.view.message.Message.REQUEST_MENU_COUNT;
import static christmas.view.message.Message.REQUEST_VISIT_DAY;
import static christmas.view.message.Message.START_MESSAGE;

import christmas.model.Menu;
import christmas.model.MenuBoard;
import java.text.DecimalFormat;
import java.util.List;

public class OutputView {
    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printRequestVisitDay() {
        System.out.println(REQUEST_VISIT_DAY);
    }

    public void printRequestOrder() {
        System.out.println(REQUEST_MENU_COUNT);
    }

    public void printOrders(List<Menu> orders) {
        System.out.println("<주문 메뉴>");
        for (Menu menu : orders) {
            System.out.println(menu.getName() + " " + menu.getCount() + "개");
        }
    }

    public void printBill(long price) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(price + "원");
    }

    public void printPromotion(MenuBoard menu) {
        System.out.println("<증정 메뉴>");
        System.out.println(menu.getName());
    }

    public void printDiscountResult(List<Long> discountPrice) {
        System.out.println("<혜택 내역>");
        DecimalFormat df = new DecimalFormat("#,###원");
        List<String> discountTypes = List.of("크리스마스 할인: ", "평일 할인: ", "주말 할인: ", "특별 할인: ", "증정 이벤트: ");

        for (int i = 0; i < discountPrice.size(); i++) {
            long discount = discountPrice.get(i);
            if (discount != 0) {
                String formattedDiscount = df.format(discount);
                String message = String.format("%s -%s", discountTypes.get(i), formattedDiscount);
                System.out.println(message);
            }
        }
    }


}
