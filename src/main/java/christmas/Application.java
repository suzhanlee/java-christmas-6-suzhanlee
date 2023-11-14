package christmas;

import christmas.config.EventFactory;
import christmas.controller.EventController;
import christmas.exception.InputMenuException;
import christmas.exception.InvalidVisitDateException;
import christmas.exception.OrderMenuTypeException;
import christmas.exception.TotalMenuNumberLimitException;
import christmas.view.InputView;
import christmas.view.output.OutputView;

public class Application {
    public static void main(String[] args) {
        EventFactory eventFactory = new EventFactory();
        try {
            InputView inputView = eventFactory.inputView();
            OutputView outputView = eventFactory.outputView();
            EventController eventController = eventFactory.eventController();
            startEvent(eventController, inputView, outputView, getVisitDayOfMonth(inputView, outputView),
                    inputView.menuAndNumber());
        } finally {
            closeResource(eventFactory);
        }
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

    private static void closeResource(EventFactory eventFactory) {
        eventFactory.consoleService().close();
    }
}
