package nus.iss.team1.grabfreshfood.service;

import jakarta.transaction.Transactional;
import nus.iss.team1.grabfreshfood.DTO.CheckoutItemReq;
import nus.iss.team1.grabfreshfood.config.CartItemCreationException;
import nus.iss.team1.grabfreshfood.config.CartItemNotFoundException;
import nus.iss.team1.grabfreshfood.config.CartItemUpdateException;
import nus.iss.team1.grabfreshfood.config.CartNotFoundException;
import nus.iss.team1.grabfreshfood.controller.GeneralRestController;
import nus.iss.team1.grabfreshfood.model.Cart;
import nus.iss.team1.grabfreshfood.model.CartItem;
import nus.iss.team1.grabfreshfood.model.OrderStatus;
import nus.iss.team1.grabfreshfood.model.Product;
import nus.iss.team1.grabfreshfood.repository.CartItemRepository;
import nus.iss.team1.grabfreshfood.repository.CartRepository;
import nus.iss.team1.grabfreshfood.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartImpl implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartImpl.class);
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

    // Updated to use Spring Data JPA derived method to strictly match both cartId
    // and cartItemId
    @Override
    public CartItem findCartItem(int cartId, int cartItemId) {
        Optional<CartItem> item = cartItemRepo.findByCartCartIdAndCartItemId(cartId, cartItemId);
        if (item.isEmpty()) {
            logger.error("CartItem (ID: " + cartItemId + ") not found in Cart (ID: " + cartId + ")");
            throw new CartItemNotFoundException("CartItem (ID: " + cartItemId + ") not found in Cart (ID: " + cartId + ")");
        }

        return item.get();
    }

    // done by Pris to add key in numbered
    @Override
    public CartItem addNumberQuantity(int customerId, int productId, int quantity) {
        Cart cart = findCartByCustomerId(customerId);
        int cartId = cart.getCartId();

        CartItem item = cartItemRepo.findCartItemsByProductId(cartId, productId);

        if (item != null) {
            // Add to existing quantity
            int newQuantity = item.getQuantity() + quantity;
            return updateItemQuantity(cartId, item.getCartItemId(), newQuantity);
        } else {
            CartItem cartItem = new CartItem();
            try {
                cartItem.setCart(cart);
                cartItem.setCheckout(true);
                cartItem.setQuantity(quantity);
                cartItem.setProductId(productId);

                cartItemRepo.saveAndFlush(cartItem);
            } catch (DataAccessException e) {
                throw new CartItemCreationException("Error while saving Cart Item to DB: " + e.getMessage());
            } catch (Exception e) {
                throw new CartItemCreationException(
                        "Unexpected error occurred while adding item to cart: " + e.getMessage());
            }

            return cartItem;
        }
    }

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

    // Done by Dionis
    @Override
    public List<CartItem> updateSelectedItems(List<Integer> selectedIds, int customerId) {
        Cart cart = findCartByCustomerId(customerId);
        List<CartItem> cartList = findCartItemsByCartId(cart.getCartId());

        List<CartItem> selectedItems = cartList.stream()
                .filter(item -> selectedIds.contains(item.getCartItemId()))
                .toList();

        List<CartItem> unSelectedItems = cartList.stream()
                .filter(item -> !selectedIds.contains(item.getCartItemId()))
                .toList();

        try {
            for (CartItem item : selectedItems) {
                item.setCheckout(true);
                cartItemRepo.saveAndFlush(item);
            }

            for (CartItem item : unSelectedItems) {
                item.setCheckout(false);
                cartItemRepo.saveAndFlush(item);
            }

            return selectedItems;
        } catch (DataAccessException e) {
            throw new CartItemUpdateException("Error updating boolean isCheckOut for Cart Item: " + e.getMessage());
        }
    }

    // Done by Lewis
    @Override
    public void deleteCartItem(int cartId, int itemId) {
        CartItem item = findCartItem(cartId, itemId);
        cartItemRepo.delete(item);
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
                throw new CartItemCreationException(
                        "Unexpected error occurred while adding item to cart: " + e.getMessage());
            }

            return cartItem;
        }
    }

    // Lst find the checkout cart item
    public List<CartItem> getCheckoutCartItems(int cartId) {
        return cartItemRepo.findCheckoutCartItemByCartId(cartId);
    }

    // Lst remove checkout item after creating new order
    public void removeCheckoutItemsFromCart(List<CartItem> checkoutItems) {
        cartItemRepo.deleteAll(checkoutItems);
    }

    public List<CheckoutItemReq> getCheckoutReq(int cartId) {
        List<CartItem> checkoutItems = getCheckoutCartItems(cartId);
        List<CheckoutItemReq> showList = new ArrayList<>();

        for (CartItem cartItem : checkoutItems) {
            Product product = productRepository.findProductById(cartItem.getProductId());
            BigDecimal unitPrice = BigDecimal.valueOf(product.getPrice()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal quantity = BigDecimal.valueOf(cartItem.getQuantity());
            BigDecimal perItemTotal = unitPrice.multiply(quantity).setScale(2, RoundingMode.HALF_UP);

            CheckoutItemReq ciReq = new CheckoutItemReq();
            ciReq.setProductName(product.getName());
            ciReq.setUnitPrice(unitPrice);
            ciReq.setQuantity(cartItem.getQuantity());
            ciReq.setPerProductTotalAmount(perItemTotal);

            showList.add(ciReq);
        }

        return showList;
    }

    public BigDecimal calculateCheckoutSum(List<CheckoutItemReq> checkoutItemReqList) {
        return checkoutItemReqList.stream().map(CheckoutItemReq::getPerProductTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add).add(OrderStatus.SERVICEFEE).setScale(2, RoundingMode.HALF_UP);
    }

    public int getCartItemCount(int customerId) {
        Cart cart = findCartByCustomerId(customerId);
        List<CartItem> cartItems = findCartItemsByCartId(cart.getCartId());
        return cartItems.size();
    }
}
