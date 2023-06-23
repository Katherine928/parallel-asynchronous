package net.katherine.parallelasynchronous.service;

import net.katherine.parallelasynchronous.domain.checkout.Cart;
import net.katherine.parallelasynchronous.domain.checkout.CartItem;
import net.katherine.parallelasynchronous.domain.checkout.CheckoutResponse;
import net.katherine.parallelasynchronous.domain.checkout.CheckoutStatus;

import java.util.List;

import static net.katherine.parallelasynchronous.util.CommonUtil.*;

public class CheckoutService {

    private PriceValidatorService priceValidatorService;

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
        return new CheckoutResponse(CheckoutStatus.SUCCESS);

    }



}
