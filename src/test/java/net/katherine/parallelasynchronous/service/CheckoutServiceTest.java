package net.katherine.parallelasynchronous.service;

import net.katherine.parallelasynchronous.domain.checkout.Cart;
import net.katherine.parallelasynchronous.domain.checkout.CheckoutResponse;
import net.katherine.parallelasynchronous.domain.checkout.CheckoutStatus;
import net.katherine.parallelasynchronous.util.DataSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {

    PriceValidatorService priceValidatorService = new PriceValidatorService();
    CheckoutService checkoutService = new CheckoutService(priceValidatorService);

    @Test
    void no_Of_cores() {
        // Getting the available processors for parallelStream, and it stops counting after exceeding the limit
        System.out.println("no of cores : " + Runtime.getRuntime().availableProcessors());
        // The number of cores is 16
    }
    @Test
    void checkout_6_items() {
        // given
        Cart cart = DataSet.createCart(6);
        // when
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);
        // then
        assertEquals(CheckoutStatus.SUCCESS, checkoutResponse.getCheckoutStatus());
    }


    @Test
    void checkout_17_items() {
        Cart cart = DataSet.createCart(17);
        System.out.println("size of the cart  : "+ cart.getCartItemList().size());
        //when
        CheckoutResponse checkoutResponse = checkoutService.checkout(cart);

        //then
        assertEquals(CheckoutStatus.FAILURE, checkoutResponse.getCheckoutStatus());
    }
}