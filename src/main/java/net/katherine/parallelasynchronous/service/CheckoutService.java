package net.katherine.parallelasynchronous.service;

import net.katherine.parallelasynchronous.domain.checkout.Cart;
import net.katherine.parallelasynchronous.domain.checkout.CartItem;
import net.katherine.parallelasynchronous.domain.checkout.CheckoutResponse;
import net.katherine.parallelasynchronous.domain.checkout.CheckoutStatus;
import java.util.List;

import static net.katherine.parallelasynchronous.util.CommonUtil.*;
import static net.katherine.parallelasynchronous.util.LoggerUtil.log;

public class CheckoutService {

    private final PriceValidatorService priceValidatorService;

    public CheckoutService(PriceValidatorService priceValidatorService) {
        this.priceValidatorService = priceValidatorService;
    }

    public CheckoutResponse checkout(Cart cart) {
        startTimer();
        List<CartItem> priveValidationList =  cart.getCartItemList()
                .parallelStream()
//                .stream()
                .peek(cartItem -> {
                   boolean isPriceInvalid = priceValidatorService.isCartItemInvalid(cartItem);
                   cartItem.setExpired(isPriceInvalid);
                })
                .filter(CartItem::isExpired)
                .toList();
        timeTaken();
        stopWatchReset();

        if(priveValidationList.size() > 0) {
            return new CheckoutResponse(CheckoutStatus.FAILURE, priveValidationList);
        }

        double finalPrice = calculateFinalPrice(cart);
        log(" Checkout Complete and the final price is " + finalPrice);

        return new CheckoutResponse(CheckoutStatus.SUCCESS, finalPrice);

    }

    private double calculateFinalPrice(Cart cart) {

        return cart.getCartItemList()
                .parallelStream()
                .map(cartItem -> cartItem.getQuantity() * cartItem.getRate())
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private double calculateFinalPrice_reduce(Cart cart) {

        return cart.getCartItemList()
                .parallelStream()
                .map(cartItem -> cartItem.getQuantity() * cartItem.getRate())
                .reduce(0.0, Double::sum);
    }

}
