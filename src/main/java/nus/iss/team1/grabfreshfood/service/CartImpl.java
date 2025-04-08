package nus.iss.team1.grabfreshfood.service;

import jakarta.transaction.Transactional;
import nus.iss.team1.grabfreshfood.config.CartNotFoundException;
import nus.iss.team1.grabfreshfood.model.Cart;
import nus.iss.team1.grabfreshfood.model.CartItem;
import nus.iss.team1.grabfreshfood.repository.CartItemRepository;
import nus.iss.team1.grabfreshfood.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartImpl implements CartService {

    private final CartRepository cartRepo;
    private final CartItemRepository cartItemRepo;

    public CartImpl(CartRepository cartRepo, CartItemRepository cartItemRepo) {
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
    }

    @Override
    //Done by Dionis Wee
    public Cart findCartByCustomerId(int customerId) {
        //find cart first by customerId
        Cart cart = cartRepo.findCartByCustomerId(customerId);

        //if cart is null to throw exception as Cart should be created for customer
        // when customer registers an account
        if (cart == null) {
            throw new CartNotFoundException("Cart does not exist for Customer ID: " + customerId);
        }

        return cart;
    }

    @Override
    //Done by Dionis Wee
    public List<CartItem> findCartItemsByCartId(int cartId) {
        return cartItemRepo.findCartItemsByCartId(cartId);

    }

}
