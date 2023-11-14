package christmas.config;

import christmas.controller.EventController;
import christmas.view.ChristmasConsoleService;
import christmas.view.ConsoleService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventFactory {

    public EventController eventController() {
        return new EventController(outputView());
    }

    public InputView inputView() {
        return new InputView(consoleService());
    }

    public ConsoleService consoleService() {
        return new ChristmasConsoleService();
    }

    public OutputView outputView() {
        return new OutputView();
    }
}
