package nus.iss.team1.grabfreshfood.repository;

import nus.iss.team1.grabfreshfood.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByProductId(int productId);

    boolean existsByProductIdAndUserId(int productId, int userId);


}
