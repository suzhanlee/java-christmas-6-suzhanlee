package christmas.dto;

public class MenuDto {

    private final String name;
    private final int quantity;

    private MenuDto(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public static MenuDto valueOf(String name, int quantity) {
        return new MenuDto(name, quantity);
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
