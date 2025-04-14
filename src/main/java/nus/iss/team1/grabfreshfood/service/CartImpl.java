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
import java.util.Optional;

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
            throw new CartItemNotFoundException("Cart item with id (" + cartItemId + ") not found in cart with id " + cartId);

        }
        return item;

    }
    
    //done by Pris
    @Override
    public CartItem addProductToCart(int customerId, int productId) {
    	CartItem item =cartItemRepo.findCartItemByProduct(customerId,productId);
    	if (item == null) {
    		CartItem newItem = new CartItem();
            newItem.setCart(cartRepo.findCartByCustomerId(customerId));
            newItem.setProductId(productId);
            newItem.setQuantity(1);
            Optional<Product> itemProduct = productRepository.findProductById(productId);
            if (itemProduct.isPresent()) {
                newItem.setPrice(itemProduct.get().getPrice());
            }
            else {
                throw new CartItemNotFoundException("Cart item with id (" + newItem.getCartId() + ") not found in product with id " + productId);

            }

            return cartItemRepo.save(newItem);

        }
    	else {
    		item.addQuantity();
    		}
    	return cartItemRepo.save(item);
    }


    @Override
    public CartItem updateItemQuantity(int cartId, int cartItemId, int quantity) {
        //extracting cart item by reusing method above
        CartItem item = findCartItem(cartId, cartItemId); //throw CartItemNotFoundException if cart not found

        try {
            //setting new quantity
            item.setQuantity(quantity);
            return cartItemRepo.save(item);
        } catch (DataAccessException e) {
            throw new CartItemUpdateException("Error occured when updating quantity of Cart Item " + e);
        }
    }

    //Done by Lewis
    // Implemented deleteCartItem method – the signature exactly matches the one in the CartService interface.
    @Override
    public String deleteCartItem(int cartId, int itemId) {
        // Retrieve the cart item (this will throw a CartItemNotFoundException if it does not exist)
        CartItem item = findCartItem(cartId, itemId);

        if (item == null) {

            throw new CartItemNotFoundException("Cart item with id (" + itemId + ") not found in cart with id " + cartId);

        }
        // Delete the cart item.
        cartItemRepo.delete(item);
        return "Successfully deleted.";
    }

    //Done by Dionis
    @Override
    public CartItem addCartItemToCart(int customerId, int productId) {
        Cart cart = findCartByCustomerId(customerId); //if null, throw CartNotFoundException here
        int cartId = cart.getCartId();

        CartItem item = cartItemRepo.findCartItemsByProductId(cartId, productId);

        //if item already exists in cart to just +1 to existing quantity
        if (item != null) {
            return updateItemQuantity(cartId, item.getCartItemId(), item.getQuantity() + 1);
        } else {


            //setting values and instantiating CartItem
            CartItem cartItem = new CartItem();
            try {
                cartItem.setCart(cart);
                cartItem.setCheckout(true);
                cartItem.setQuantity(1);
                cartItem.setProductId(productId);

                cartItemRepo.saveAndFlush(cartItem);

                
            } catch (DataAccessException e) {
                throw new CartItemCreationException("Error while saving Cart Item to DB: " + e);

            } catch (Exception e) {
                throw new CartItemCreationException("Error occured while adding item to cart: " + e);
            }

            return cartItem;

        }
    }
}
