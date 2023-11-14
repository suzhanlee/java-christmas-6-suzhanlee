package christmas;

import christmas.controller.EventController;
import christmas.exception.InputMenuException;
import christmas.exception.InvalidVisitDateException;
import christmas.exception.OrderMenuTypeException;
import christmas.exception.TotalMenuNumberLimitException;
import christmas.view.ChristmasConsoleService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(new ChristmasConsoleService());
        OutputView outputView = new OutputView();
        EventController eventController = new EventController(new OutputView());
        startEvent(eventController, inputView, outputView, getVisitDayOfMonth(inputView, outputView), inputView.menuAndNumber());
    }

    public static int getVisitDayOfMonth(InputView inputView, OutputView outputView) {
        try {
            return inputView.visitDayOfMonth();
        } catch (InvalidVisitDateException e) {
            outputView.printErrorMessage(e.getMessage());
            return getVisitDayOfMonth(inputView, outputView);
        }
    }

    private static void startEvent(EventController eventController, InputView inputView, OutputView outputView, int visitDayOfMonth,
                                   String menuForm) {
        try {
            eventController.startEvent(visitDayOfMonth, menuForm);
        } catch (InputMenuException | OrderMenuTypeException | TotalMenuNumberLimitException e) {
            outputView.printErrorMessage(e.getMessage());
            startEvent(eventController, inputView, outputView, visitDayOfMonth, inputView.menuAndNumber());
        }
    }
}
