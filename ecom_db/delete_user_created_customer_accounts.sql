SET @customerId := 104;

-- Delete cart items belonging to customer's cart(s)
DELETE ci FROM shopping_cart_item ci
JOIN cart c ON ci.cart_id = c.cart_id
WHERE c.customer_id = @customerId;

-- Delete cart(s)
DELETE FROM cart WHERE customer_id = @customerId;

-- Delete the customer record
DELETE FROM customer WHERE customer_id = @customerId;
