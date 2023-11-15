package christmas.config;

import christmas.controller.EventController;
import christmas.service.EventService;
import christmas.view.input.ChristmasConsoleService;
import christmas.view.input.ConsoleService;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class EventFactory {

    public EventController eventController() {
        return new EventController(outputView(), eventService());
    }

    public OutputView outputView() {
        return new OutputView();
    }

    private EventService eventService() {
        return new EventService();
    }

    public InputView inputView() {
        return new InputView(consoleService());
    }

    public ConsoleService consoleService() {
        return new ChristmasConsoleService();
    }
}
