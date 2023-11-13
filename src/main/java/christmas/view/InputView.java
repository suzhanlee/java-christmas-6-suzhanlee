package christmas.view;

public class InputView {

    public static final String INPUT_VISIT_DATE_MENT = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String INVALID_VISIT_DATE_EXCEPTION = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String INPUT_MENU_AND_NUMBER_MENT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private final ConsoleService consoleService;

    public InputView(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    public int visitDate() {
        try {
            System.out.println(INPUT_VISIT_DATE_MENT);
            int visitDayOfMonth = Integer.parseInt(consoleService.readLine());
            if (visitDayOfMonth < 1 || visitDayOfMonth >= 31) {
                throw new IllegalStateException(INVALID_VISIT_DATE_EXCEPTION);
            }
            return visitDayOfMonth;
        } catch (NumberFormatException e) {
            throw new IllegalStateException(INVALID_VISIT_DATE_EXCEPTION);
        }
    }

    public String menuAndNumber() {
        System.out.println(INPUT_MENU_AND_NUMBER_MENT);
        return consoleService.readLine();
    }
}
