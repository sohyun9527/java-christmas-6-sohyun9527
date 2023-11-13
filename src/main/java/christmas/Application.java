package christmas;

import christmas.controller.ChristmasEventController;
import christmas.service.ChristmasEventService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ChristmasEventService christmasEventService = new ChristmasEventService();
        ChristmasEventController christMasEventController =
                new ChristmasEventController(inputView, outputView, christmasEventService);
        christMasEventController.run();
    }
}
