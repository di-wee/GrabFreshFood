import React, { useEffect, useState } from 'react';
import CartItem from './CartItem';
import CartSummary from './CartSummary';
import { Button } from 'react-bootstrap';

function ShoppingCart() {
	//state management
	const [customerId, setCustomerId] = useState(null);
	const [cartItems, setCartItems] = useState([]);
	const [subtotal, setSubTotal] = useState(0);
	const [itemPrice, setItemPrice] = useState({}); // key value pair to contain id: price
	const [quantities, setQuantities] = useState({}); //key value pair to contain id: quantity
	const [selectedItems, setSelectedItems] = useState([]); //array to contain id of selected items
	//note that on checkout to pass selecteditems and quantities

	const serviceFee = 3.6;
	const total = (subtotal + serviceFee).toFixed(2);

	// helper function to calculate subtotal per item
	const calculateItemSubtotal = (id) => {
		const quantity = quantities[id] || 0;
		const price = itemPrice[id] || 0;
		return (quantity * price).toFixed(2);
	};

	const handleEmptyCart = async () => {
		// Lewis: Added confirmation popup before proceeding to empty the cart
		const confirmEmpty = window.confirm(
			'Are you sure you want to empty your cart?'
		);
		if (!confirmEmpty) return;

		//logic for onClick of 'Empty Cart' button goes here.
		// for this logic, take note that u have to remove ALL items in cart.
		// so to start off, u can loop through the items in cart via the 'cartItems' variable where i
		// used to store all the cartitem info into an array.
		// u open up your dev tools/inspect tools in your browser to check console.logs for references
		// to what is being pulled.
		//
		// call the DEL API, u can either use axios or use fetch to do so, can refer to my codes if anything.
		try {
			await Promise.all(
				cartItems.map((item) => {
					const url =
						import.meta.env.VITE_SERVER +
						`api/cart/${item.cartId}/item/${item.cartItemId}`;
					return fetch(url, { method: 'DELETE' });
				})
			);
			// Clear all cart related state after deletion
			setCartItems([]);
			setSelectedItems([]);
			setQuantities({});
			setItemPrice({});
			setSubTotal(0);
			console.log('Empty cart successfully');
		} catch (err) {
			console.error('Error emptying cart: ', err);
		}
	};

	//checkout logic while calling POST api and redirecting to checkout page
	const handleCheckout = async () => {
		//filter array for only those that have been selected

		const reqBody = {
			selectedIds: selectedItems,
			customerId,
		};

		const url = import.meta.env.VITE_SERVER + 'api/cart/update-selected';

		try {
			const res = await fetch(url, {
				method: 'PUT',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify(reqBody),
			});
			if (!res.ok) throw new Error('Error creating order!');
			const data = await res.json(); //retrieving the orderId from response body
			console.log('Selected items: ', data);
			window.location.href = import.meta.env.VITE_SERVER + `checkout-page`;
		} catch (err) {
			console.error('Error: ', err);
		}
	};

	//fetching GET customerId
	const fetchCustomerId = async () => {
		try {
			const url = import.meta.env.VITE_SERVER + 'api/session/customer-id';
			const res = await fetch(url);
			if (!res.ok) throw new Error('Error getting cart items!');
			const data = await res.json();
			//checking
			console.log('Customer ID: ', data);
			setCustomerId(data);
			return data;
		} catch (err) {
			console.error('Error fetching customer ID: ', err);
		}
	};

	//fetch cart items
	const fetchCartItems = async (id) => {
		try {
			const url = import.meta.env.VITE_SERVER + `api/cart/customer/${id}/items`;
			const res = await fetch(url);
			if (!res.ok) throw new Error('Error getting cart items');
			const items = await res.json();

			console.log('Cart: ', items);

			//to preselect all items in checkbox, first extract out cartItemId into array
			const preSelectAllItems = items.map((item) => item.cartItemId);
			setSelectedItems(preSelectAllItems);

			//setting quantity of items
			const initialQuantity = {};
			//populate initalQuantity with existing item quantity or default to 1
			items.forEach((item) => {
				initialQuantity[item.cartItemId] = item.quantity || 1;
			});
			setQuantities(initialQuantity);

			setCartItems(items);
			return items;
		} catch (err) {
			console.error('Error fetching cart items:', err);
		}
	};

	// Lewis: Callback to remove a cart item from local state after deletion
	const removeCartItem = (itemId) => {
		setCartItems((prev) => prev.filter((item) => item.cartItemId !== itemId));
		setSelectedItems((prev) => prev.filter((id) => id !== itemId));
		setQuantities((prev) => {
			const newQuantities = { ...prev };
			delete newQuantities[itemId];
			return newQuantities;
		});
		setItemPrice((prev) => {
			const newPrices = { ...prev };
			delete newPrices[itemId];
			return newPrices;
		});
	};

	useEffect(() => {
		const loadCart = async () => {
			const id = await fetchCustomerId();
			console.log('id: ', id);
			if (id) {
				await fetchCartItems(id);
			}
		};
		loadCart();
	}, []);

	return (
		<>
			{cartItems.length === 0 ? (
				<div
					className='empty container'
					style={{
						marginTop: '20vh',
						display: 'flex',
						flexDirection: 'column',
						justifyContent: 'center',
						alignItems: 'center',
					}}>
					<img
						style={{ width: '20%' }}
						src='http://localhost:8080/assets/cart.jpg'
						alt='Empty cart'
					/>
					<h5>Your cart is empty</h5>
					<p>Add items into your shopping cart and they will appear here.</p>
					<button
						onClick={() => (window.location.href = 'http://localhost:8080/')}
						className='btn btn-success btn-sm'>
						Start Shopping
					</button>
				</div>
			) : (
				<div className='main container my-5'>
					<div className='d-flex justify-content-between align-items-center mb-4'>
						<h4 className='mb-0'>Your Shopping Cart</h4>
						<button
							onClick={handleEmptyCart}
							className='btn btn-outline-danger btn-sm'>
							<i className='bi bi-trash3 me-2'></i>
							Empty cart
						</button>
					</div>

					<form>
						<div className='row'>
							{/* Cart Items */}
							<div className='col-md-8 mb-4'>
								<div className='bg-white p-3 rounded shadow-sm'>
									{cartItems.map((item) => (
										<CartItem
											key={item.cartItemId}
											item={item}
											selectedItems={selectedItems}
											setSelectedItems={setSelectedItems}
											setQuantities={setQuantities}
											quantities={quantities}
											setSubTotal={setSubTotal}
											subtotal={subtotal}
											cartItems={cartItems}
											itemPrice={itemPrice}
											setItemPrice={setItemPrice}
											removeCartItem={removeCartItem}
											itemSubtotal={calculateItemSubtotal(item.cartItemId)}
										/>
									))}
								</div>
							</div>

							{/* Summary Panel */}
							<div className='col-md-4'>
								<div className='bg-white p-4 rounded shadow-sm'>
									<CartSummary
										subtotal={subtotal}
										serviceFee={serviceFee}
										total={total}
										selectedCount={selectedItems.length}
									/>

									<Button
										onClick={handleCheckout}
										variant='success'
										className='w-100 d-flex justify-content-between align-items-center px-4 py-3 mt-3'>
										<span><strong>Checkout</strong></span>
										<span><strong>${total}</strong></span>
									</Button>
								</div>
							</div>
						</div>
					</form>
				</div>

			)}
		</>
	);
}

export default ShoppingCart;
