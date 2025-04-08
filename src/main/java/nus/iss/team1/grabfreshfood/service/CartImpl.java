package nus.iss.team1.grabfreshfood.service;

import jakarta.transaction.Transactional;
import nus.iss.team1.grabfreshfood.config.CartItemNotFoundException;
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
    //Done by Dionis
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
    //Done by Dionis
    public List<CartItem> findCartItemsByCartId(int cartId) {
        return cartItemRepo.findCartItemsByCartId(cartId);

    }

    @Override
    //Done by Dionis
    public CartItem findCartItem(int cartId, int cartItemId) {
        //extracting item from db via cartId and cartItemId
        CartItem item = cartItemRepo.findCartItem(cartId, cartItemId);

        //item should exist, else throw exception
        if (item == null) {
            throw new CartItemNotFoundException("Cart item of id (" + cartItemId + ") from Cart (id: " + cartId + " ) does not exist.");

        }
        return item;

    }

    @Override
    public CartItem updateItemQuantity(int cartId, int cartItemId, int quantity) {
        //extracting cart item by reusing method above
        CartItem item = findCartItem(cartId, cartItemId);

        //setting new quantity
        item.setQuantity(quantity);
        return cartItemRepo.save(item);
    }


}
