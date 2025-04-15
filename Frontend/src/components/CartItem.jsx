import React, { useEffect, useState } from 'react';

function CartItem({
	item,
	selectedItems,
	setSelectedItems,
	setQuantities,
	quantities,
	setSubTotal,
	subTotal,
	cartItems,
	itemPrice,
	setItemPrice,
	// Lewis: Added removeCartItem callback to allow parent state update after deletion
	removeCartItem,
}) {
	//state management
	const [product, setProduct] = useState({});
	const [localQuantity, setLocalQuantity] = useState(item.quantity);

	// delete one item only.
	const handleDeleteItem = async (cartId, itemId) => {
		// Lewis: Added confirmation prompt before deletion
		const confirmDelete = window.confirm(
			'Are you sure you want to remove this item from your cart?'
		);
		if (!confirmDelete) return;

		try {
			const url =
				import.meta.env.VITE_SERVER + `api/cart/${cartId}/item/${itemId}`;
			const res = await fetch(url, { method: 'DELETE' });
			if (!res.ok) throw new Error('Error deleting cart item');
			console.log('Deleted cart item: ', itemId);
			// Lewis: callback to remove the deleted item from parent state
			if (removeCartItem) {
				removeCartItem(itemId);
			}
		} catch (err) {
			console.error('Error deleting item: ', err);
		}
	};

	//handling of updating of item quantity
	function handleUpdateQuantity(itemId, newQuantity) {
		setLocalQuantity(newQuantity);

		setQuantities((prev) => ({
			...prev,
			[itemId]: newQuantity,
		}));

		console.log(quantities);
	}

	// handling toggling of checkbox
	function handleToggleCheckBox(itemId) {
		if (selectedItems.includes(itemId)) {
			//if item is already selected, remove it.
			setSelectedItems(selectedItems.filter((id) => id !== itemId));
		} else {
			//or else add the item to state
			setSelectedItems([...selectedItems, itemId]); // [1, 2 ,4]
		}
	}

	const updateQuantityDB = async (cartId, cartItemId, quantity) => {
		try {
			const url = import.meta.env.VITE_SERVER + 'api/cart/update-quantity';
			const res = await fetch(url, {
				method: 'PUT',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify({
					cartId,
					cartItemId,
					quantity,
				}),
			});
			if (!res.ok) throw new Error('Error updating quantity to db');
			console.log('Quantity updated for item: ', cartItemId);
		} catch (err) {
			console.error('Error: ', err);
		}
	};

	const updateLocalItemPrice = (cartItemId, price) => {
		setItemPrice((prev) => ({
			...prev,
			[cartItemId]: price,
		}));
	};

	//fetching product details
	const fetchProduct = async () => {
		try {
			const url = import.meta.env.VITE_SERVER + `api/product/${item.productId}`;

			const res = await fetch(url);
			if (!res.ok) throw new Error('Error getting product details!');
			const data = await res.json();
			console.log('Product: ', data);
			setProduct(data);

			//setting state to store accurate price of product
			updateLocalItemPrice(item.cartItemId, data.price);
			return data;
		} catch (err) {
			console.error('Error: ', err);
		}
	};

	useEffect(() => {
		fetchProduct();
	}, []);

	useEffect(() => {
		let total = 0;
		for (let item of cartItems) {
			//if selectedItems include the particular cartItemId
			if (selectedItems.includes(item.cartItemId)) {
				//extract quantities in state via itemId
				const quant = quantities[item.cartItemId];
				const price = itemPrice[item.cartItemId];
				const itemTotal = quant * price;
				total += itemTotal;
			}
		}
		setSubTotal(total);
	}, [cartItems, quantities, selectedItems, itemPrice]);

	return (
		<div className='row border-bottom p-3 align-items-center'>
			<div className='product-checkbox col-1 d-flex justify-content-center'>
				<input
					className='form-check-input'
					type='checkbox'
					checked={selectedItems.includes(item.cartItemId)}
					onChange={() => handleToggleCheckBox(item.cartItemId)}
				/>
			</div>
			<div className='product-image col-2'>
				<img
					alt='Product'
					className='img-fluid'
					src=''
				/>
			</div>
			<div className='product-name col-7'>
				<h6>
					<b>{product.name}</b>
				</h6>
				<p style={{ fontSize: 'smaller' }}>
					<i>{product.description}</i>
				</p>
				<p style={{ fontSize: 'smaller' }}>
					$
					{product.price !== undefined
						? product.price.toFixed(2)
						: product.price}
				</p>
				<input
					className='form-control form-control-sm text-center'
					min='1'
					max='10'
					style={{ width: '80px' }}
					type='number'
					value={quantities[item.cartItemId]}
					onChange={(event) => {
						setLocalQuantity(parseInt(event.target.value));
						handleUpdateQuantity(item.cartItemId, parseInt(event.target.value));
					}}
					//onBlur used here so as to not overload DB by
					// updating quantity to DB on every onChange
					onBlur={() => {
						const qty = parseInt(localQuantity);
						if (!isNaN(qty) && qty >= 1 && qty <= 10) {
							updateQuantityDB(item.cartId, item.cartItemId, qty);
						} else {
							console.warn('invalid quantity entered');
						}
					}}
				/>
			</div>
			<div
				className='price-delete col-1 d-flex flex-column align-items-center'
				style={{ marginLeft: '3vh' }}>
				<h6 className='mb-1'>
					<b>
						{/* to cater for async nature of product due to it being fetched from api */}
						$
						{product.price !== undefined
							? product.price.toFixed(2)
							: product.price}
					</b>
				</h6>
				{/* Updated onClick handler to pass proper parameters */}
				<i
					onClick={() => handleDeleteItem(item.cartId, item.cartItemId)}
					className='bi bi-trash3'
					style={{ cursor: 'pointer' }}></i>
			</div>
		</div>
	);
}

export default CartItem;
