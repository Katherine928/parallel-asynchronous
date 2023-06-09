package net.katherine.parallelasynchronous.service;

import net.katherine.parallelasynchronous.domain.Review;

import static net.katherine.parallelasynchronous.util.CommonUtil.delay;

public class ReviewService {

    public Review retrieveReviews(String productId) {
        delay(1000);
        return new Review(200, 4.5);
    }
}
