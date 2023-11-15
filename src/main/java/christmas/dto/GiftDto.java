package christmas.dto;

public class GiftDto {

    private final String name;
    private final int quantity;

    private GiftDto(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public static GiftDto valueOf(String name, int quantity) {
        return new GiftDto(name, quantity);
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
