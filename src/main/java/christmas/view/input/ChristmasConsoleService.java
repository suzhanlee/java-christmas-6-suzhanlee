package christmas.view.input;

import camp.nextstep.edu.missionutils.Console;

public class ChristmasConsoleService implements ConsoleService {

    @Override
    public String readLine() {
        return Console.readLine();
    }

    @Override
    public void close() {
        Console.close();
    }
}
