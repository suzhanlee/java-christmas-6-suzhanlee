package christmas.controller;

import christmas.dto.MenuInfoDto;
import christmas.dto.PreviewEventBenefitsDto;
import christmas.service.EventService;
import christmas.view.output.OutputView;

public class EventController {
    private final OutputView outputView;
    private final EventService eventService;

    public EventController(OutputView outputView, EventService eventService) {
        this.outputView = outputView;
        this.eventService = eventService;
    }

    public void startEvent(int visitDayOfMonth, String menuForm) {
        printEventBenefitsPreview(visitDayOfMonth, eventService.previewEventBenefits(visitDayOfMonth, menuForm));
    }

    private void printEventBenefitsPreview(int visitDayOfMonth, PreviewEventBenefitsDto previewEventBenefitsDto) {
        MenuInfoDto menuInfoDto = previewEventBenefitsDto.getMenuInfoDto();

        outputView.printEventBenefitPreviewMessage(visitDayOfMonth);
        outputView.printOrderMenus(previewEventBenefitsDto.getMenuDtos());
        outputView.printTotalOrderAmount(previewEventBenefitsDto.getTotalOrderAmount());
        outputView.printGifts(menuInfoDto.getGiftDtos());
        outputView.printBenefitDetails(menuInfoDto.getBenefitDetails());
        outputView.printTotalDiscountAmount(menuInfoDto.getTotalDiscountAmount());
        outputView.printExpectedPaymentAmount(menuInfoDto.getExpectedPaymentAmount());
        outputView.printEventBadge(menuInfoDto.getEventBadgeDto().getName());
    }
}
