package nus.iss.team1.grabfreshfood.service;

import jakarta.transaction.Transactional;
import nus.iss.team1.grabfreshfood.config.CartItemCreationException;
import nus.iss.team1.grabfreshfood.config.CartItemNotFoundException;
import nus.iss.team1.grabfreshfood.config.CartItemUpdateException;
import nus.iss.team1.grabfreshfood.config.CartNotFoundException;
import nus.iss.team1.grabfreshfood.model.Cart;
import nus.iss.team1.grabfreshfood.model.CartItem;
import nus.iss.team1.grabfreshfood.model.Customer;
import nus.iss.team1.grabfreshfood.model.Product;
import nus.iss.team1.grabfreshfood.repository.CartItemRepository;
import nus.iss.team1.grabfreshfood.repository.CartRepository;

import nus.iss.team1.grabfreshfood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CartImpl implements CartService {

    private final CartRepository cartRepo;
    private final CartItemRepository cartItemRepo;
    private final ProductRepository productRepository;

    public CartImpl(CartRepository cartRepo, CartItemRepository cartItemRepo, ProductRepository productRepository) {
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
        this.productRepository = productRepository;
    }

    // Done by Dionis
    @Override
    public Cart findCartByCustomerId(int customerId) {
        Cart cart = cartRepo.findCartByCustomerId(customerId);
        if (cart == null) {

            throw new CartNotFoundException("Cart does not exist for Customer ID: " + customerId);
        }
        return cart;
    }

    // Done by Dionis
    @Override
    public List<CartItem> findCartItemsByCartId(int cartId) {
        return cartItemRepo.findCartItemsByCartId(cartId);
    }

    // Updated to use Spring Data JPA derived method to strictly match both cartId and cartItemId
    @Override
    public CartItem findCartItem(int cartId, int cartItemId) {
        return cartItemRepo.findByCartCartIdAndCartItemId(cartId, cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException(
                        "Cart item with id (" + cartItemId + ") not found in cart with id " + cartId));
    }
    
    //done by Pris
//    @Override
//    public CartItem addProductToCart(int customerId, int productId) {
//    	CartItem item =cartItemRepo.findCartItemByProduct(customerId,productId);
//    	if (item == null) {
//    		CartItem newItem = new CartItem();
//            newItem.setCart(cartRepo.findCartByCustomerId(customerId));
//            newItem.setProductId(productId);
//            newItem.setQuantity(1);
//
//            return cartItemRepo.save(newItem);
//
//        }
//    	else {
//    		item.addQuantity();
//    		}
//    	return cartItemRepo.save(item);
//    }

    @Override
    public CartItem updateItemQuantity(int cartId, int cartItemId, int quantity) {
        CartItem item = findCartItem(cartId, cartItemId);
        try {
            item.setQuantity(quantity);
            return cartItemRepo.save(item);
        } catch (DataAccessException e) {
            throw new CartItemUpdateException("Error occurred when updating quantity of Cart Item: " + e.getMessage());
        }
    }

    // Done by Lewis
    @Override
    public String deleteCartItem(int cartId, int itemId) {
        CartItem item = findCartItem(cartId, itemId);
        cartItemRepo.delete(item);
        return "Successfully deleted.";
    }

    // Done by Dionis
    @Override
    public CartItem addCartItemToCart(int customerId, int productId) {
        Cart cart = findCartByCustomerId(customerId);
        int cartId = cart.getCartId();

        CartItem item = cartItemRepo.findCartItemsByProductId(cartId, productId);

        if (item != null) {
            return updateItemQuantity(cartId, item.getCartItemId(), item.getQuantity() + 1);
        } else {
            CartItem cartItem = new CartItem();
            try {
                cartItem.setCart(cart);
                cartItem.setCheckout(true);
                cartItem.setQuantity(1);
                cartItem.setProductId(productId);

                cartItemRepo.saveAndFlush(cartItem);
            } catch (DataAccessException e) {
                throw new CartItemCreationException("Error while saving Cart Item to DB: " + e.getMessage());
            } catch (Exception e) {
                throw new CartItemCreationException("Unexpected error occurred while adding item to cart: " + e.getMessage());
            }

            return cartItem;
        }
    }
}
