package christmas;

import christmas.controller.EventController;
import christmas.view.ChristmasConsoleService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(new ChristmasConsoleService());
        EventController eventController = new EventController(new OutputView());
        startEvent(eventController, inputView, getVisitDayOfMonth(inputView), inputView.menuAndNumber());
    }

    public static int getVisitDayOfMonth(InputView inputView) {
        try {
            return inputView.visitDayOfMonth();
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getVisitDayOfMonth(inputView);
        }
    }

    private static void startEvent(EventController eventController, InputView inputView, int visitDayOfMonth,
                                   String menuForm) {
        try {
            eventController.startEvent(visitDayOfMonth, menuForm);
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            startEvent(eventController, inputView, visitDayOfMonth, inputView.menuAndNumber());
        }
    }
}
