package nus.iss.team1.grabfreshfood.service;

import jakarta.transaction.Transactional;
import nus.iss.team1.grabfreshfood.model.Cart;
import nus.iss.team1.grabfreshfood.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CartImpl implements CartService {
    @Autowired
    private CartRepository cartRepo;

    @Override
    //Done by Dionis Wee
    public Cart findCartById(Long id) {
        return cartRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Cart with ID (" + id + ") does not exist."));
    }

}
