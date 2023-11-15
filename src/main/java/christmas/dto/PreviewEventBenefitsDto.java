package christmas.dto;

import java.util.List;
import java.util.Map;

public class PreviewEventBenefitsDto {

    private final List<MenuDto> menuDtos;
    private final List<GiftDto> giftDtos;
    private final Map<String, Long> benefitDetails;
    private final long totalDiscountAmount;
    private final long expectedPaymentAmount;
    private final EventBadgeDto eventBadgeDto;

    public PreviewEventBenefitsDto(List<MenuDto> menuDtos, List<GiftDto> giftDtos, Map<String, Long> benefitDetails,
                                   long totalDiscountAmount, long expectedPaymentAmount, EventBadgeDto eventBadgeDto) {
        this.menuDtos = menuDtos;
        this.giftDtos = giftDtos;
        this.benefitDetails = benefitDetails;
        this.totalDiscountAmount = totalDiscountAmount;
        this.expectedPaymentAmount = expectedPaymentAmount;
        this.eventBadgeDto = eventBadgeDto;
    }
}
