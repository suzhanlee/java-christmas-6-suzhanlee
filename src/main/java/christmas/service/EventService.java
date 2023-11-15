package christmas.service;

import christmas.domain.Menu;
import christmas.domain.Menus;
import christmas.domain.OrderedMenuInfo;
import christmas.domain.WooWaRestaurant;
import christmas.domain.discount.DiscountAmount;
import christmas.domain.discount.GiftEvent;
import christmas.domain.discount.policy.ChristmasDDayDiscountPolicy;
import christmas.domain.discount.policy.DiscountPolicy;
import christmas.domain.discount.policy.SpecialDiscountPolicy;
import christmas.domain.discount.policy.WeekDayDiscountPolicy;
import christmas.domain.discount.policy.WeekendDiscountPolicy;
import christmas.dto.EventBadgeDto;
import christmas.dto.GiftDto;
import christmas.dto.MenuDto;
import christmas.dto.MenuInfoDto;
import christmas.dto.PreviewEventBenefitsDto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EventService {

    public static final int DISCOUNT_YEAR = 2023;
    public static final int DISCOUNT_MONTH = 12;

    public PreviewEventBenefitsDto previewEventBenefits(int visitDayOfMonth, String menuForm) {
        LocalDate visitDate = createVisitDate(visitDayOfMonth);
        WooWaRestaurant wooWaRestaurant = createWooWaRestaurant(visitDate);
        Menus menus = wooWaRestaurant.findMenusBy(menuForm);
        OrderedMenuInfo menuInfo = wooWaRestaurant.order(visitDate, menus);
        return createPreviewEventBenefitsDto(menus, menuInfo);
    }

    private LocalDate createVisitDate(int visitDayOfMonth) {
        return LocalDate.of(DISCOUNT_YEAR, DISCOUNT_MONTH, visitDayOfMonth);
    }

    private WooWaRestaurant createWooWaRestaurant(LocalDate visitDate) {
        return new WooWaRestaurant(new DiscountAmount(createDiscountPolicies(visitDate), new GiftEvent()));
    }

    private List<DiscountPolicy> createDiscountPolicies(LocalDate visitDate) {
        List<DiscountPolicy> discountPolicies = new ArrayList<>();
        discountPolicies.add(new ChristmasDDayDiscountPolicy(visitDate));
        discountPolicies.add(new WeekDayDiscountPolicy(visitDate));
        discountPolicies.add(new WeekendDiscountPolicy(visitDate));
        discountPolicies.add(new SpecialDiscountPolicy(visitDate));
        return discountPolicies;
    }

    private PreviewEventBenefitsDto createPreviewEventBenefitsDto(Menus menus, OrderedMenuInfo menuInfo) {
        List<MenuDto> menuDtos = createMenuDtos(menus);
        List<GiftDto> giftDtos = createGiftDtos(menuInfo.gifts());
        Map<String, Long> benefitDetails = menuInfo.benefitDetails();
        long totalDiscountAmount = menuInfo.totalDiscountAmount();
        long expectedPaymentAmount = menuInfo.expectedPaymentAmount();
        EventBadgeDto eventBadgeDto = EventBadgeDto.valueOf(menuInfo.eventBadge());
        return new PreviewEventBenefitsDto(menuDtos, menus.totalOrderAmount(),
                new MenuInfoDto(giftDtos, benefitDetails, totalDiscountAmount, expectedPaymentAmount, eventBadgeDto));
    }

    private List<MenuDto> createMenuDtos(Menus menus) {
        List<MenuDto> menuDtos = new ArrayList<>();
        menus.getMenus().entrySet().forEach(menuAndQuantity -> addMenusDtoTo(menuAndQuantity, menuDtos));
        return menuDtos;
    }

    private void addMenusDtoTo(Entry<Menu, Integer> menuAndQuantity, List<MenuDto> menuDtos) {
        String menuName = menuAndQuantity.getKey().getName();
        Integer quantity = menuAndQuantity.getValue();
        menuDtos.add(MenuDto.valueOf(menuName, quantity));
    }

    private List<GiftDto> createGiftDtos(Map<Menu, Integer> gifts) {
        List<GiftDto> giftDtos = new ArrayList<>();
        gifts.entrySet().forEach(giftAndQuantity -> addGiftDtoTo(giftAndQuantity, giftDtos));
        return giftDtos;
    }

    private void addGiftDtoTo(Entry<Menu, Integer> giftAndQuantity, List<GiftDto> giftDtos) {
        String giftName = giftAndQuantity.getKey().getName();
        Integer quantity = giftAndQuantity.getValue();
        giftDtos.add(GiftDto.valueOf(giftName, quantity));
    }
}
