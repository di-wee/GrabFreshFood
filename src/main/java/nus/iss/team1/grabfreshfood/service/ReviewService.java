//DONE BY BEN YEN

package nus.iss.team1.grabfreshfood.service;

import nus.iss.team1.grabfreshfood.model.Review;
import java.util.List;

public interface ReviewService {
    List<Review> getReviewsByProductId(int productId);
    boolean hasUserReviewedProduct(int productId, int userId);
    Review addReview(Review review);
    double getAverageRating(int productId);
}
