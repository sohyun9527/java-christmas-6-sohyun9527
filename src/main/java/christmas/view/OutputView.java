package christmas.view;

import static christmas.view.message.Message.REQUEST_MENU_COUNT;
import static christmas.view.message.Message.REQUEST_VISIT_DAY;
import static christmas.view.message.Message.START_MESSAGE;

import christmas.model.Menu;
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


}
