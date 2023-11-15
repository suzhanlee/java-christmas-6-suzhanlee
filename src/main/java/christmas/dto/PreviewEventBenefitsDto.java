package christmas.dto;

import java.util.List;

public class PreviewEventBenefitsDto {

    private final List<MenuDto> menuDtos;
    private final long totalOrderAmount;
    private final MenuInfoDto menuInfoDto;

    public PreviewEventBenefitsDto(List<MenuDto> menuDtos, long totalOrderAmount, MenuInfoDto menuInfoDto) {
        this.menuDtos = menuDtos;
        this.totalOrderAmount = totalOrderAmount;
        this.menuInfoDto = menuInfoDto;
    }

    public List<MenuDto> getMenuDtos() {
        return menuDtos;
    }

    public long getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public MenuInfoDto getMenuInfoDto() {
        return menuInfoDto;
    }
}
