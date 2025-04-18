package nus.iss.team1.grabfreshfood.service;

import nus.iss.team1.grabfreshfood.model.Review;
import nus.iss.team1.grabfreshfood.repository.ReviewRepository;
import nus.iss.team1.grabfreshfood.DTO.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getReviewsByProductId(int productId) {
        return reviewRepository.findByProductId(productId);
    }

    @Override
    public boolean hasUserReviewedProduct(int productId, int userId) {
        return reviewRepository.existsByProductIdAndUserId(productId, userId);
    }

    @Override
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public double getAverageRating(int productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);
        if (reviews.isEmpty()) return 0.0;
        double sum = reviews.stream().mapToInt(Review::getRating).sum();
        return sum / reviews.size();
    }

    private Review dtoToEntity(ReviewDTO dto) {
        Review review = new Review();
        review.setProductId(dto.getProductId());
        review.setUserId(dto.getUserId());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        return review;
    }

    private ReviewDTO entityToDto(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setProductId(review.getProductId());
        dto.setUserId(review.getUserId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        return dto;
    }
}
