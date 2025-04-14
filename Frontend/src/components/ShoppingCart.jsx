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

	const handleEmptyCart = async () => {
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
		};

		const url = import.meta.env.VITE_SERVER + 'api/order/create';

		try {
			const res = await fetch(url, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify(reqBody),
			});
			if (!res.ok) throw new Error('Error creating order!');
			const data = await res.json(); //retrieving the orderId from response body
			console.log('Order created with Order ID: ', data);
			window.location.href =
				import.meta.env.VITE_SERVER + `checkout-page?orderId=${data}`;
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
				<div className='main container'>
					<div className='title row'>
						<div className='col'>
							<h5>Shopping Cart</h5>
						</div>
						<div className='col text-right'>
							{/* Updated Empty Cart button to call handleEmptyCart */}
							<button
								onClick={() => handleEmptyCart()}
								className='btn btn-outline-danger'>
								<span style={{ marginRight: '5px' }}>
									<i className='bi bi-trash3'></i>
								</span>
								Empty cart
							</button>
						</div>
					</div>

					<div className='container'>
						<form>
							<div className='row mt-4'>
								{/* Shopping cart grid */}
								<div className='col-md-7 pr-md-3'>
									<div
										className='border bg-light rounded overflow-hidden mb-2'
										style={{ padding: '1vh 1vh 0 1vh' }}>
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
												// NEW: Pass the removeCartItem callback so that deletion updates the UI
												removeCartItem={removeCartItem}
											/>
										))}
									</div>
								</div>

								{/* Subtotal grid */}
								<div className='col-md-5 pl-md-3'>
									<CartSummary
										subtotal={subtotal}
										setSubTotal={setSubTotal}
										serviceFee={serviceFee}
										total={total}
									/>
									<div className='pt-0 mt-3'>
										<Button
											onClick={() => handleCheckout()}
											variant='success'
											className='w-100 d-flex justify-content-between align-items-center px-4 py-2'>
											<span>
												<b>Checkout</b>
											</span>
											<span>
												<b>${total}</b>
											</span>
										</Button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			)}
		</>
	);
}

export default ShoppingCart;
