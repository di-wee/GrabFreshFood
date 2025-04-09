import React, { useEffect, useState } from 'react';

function CartItem({ item, selectedItems, setSelectedItems }) {
	//state management
	const [product, setProduct] = useState({});

	function handleToggleCheckBox(itemId) {
		if (selectedItems.includes(itemId)) {
			//if item is already selected, remove it.
			setSelectedItems(selectedItems.filter((id) => id !== itemId));
		} else {
			//or else add the item to state
			setSelectedItems([...selectedItems, itemId]);
		}
	}

	//fetching product details
	const fetchProduct = async () => {
		try {
			const url = import.meta.env.VITE_SERVER + `api/product/${item.productId}`;

			const res = await fetch(url);
			if (!res.ok) throw new Error('Error getting product details!');
			const data = await res.json();
			console.log('Product: ', data);
			setProduct(data);
			return data;
			console.log('product state: ', product);
		} catch (err) {
			console.error('Error: ', err);
		}
	};

	useEffect(() => {
		fetchProduct();
	}, []);

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
					<b>{product.productName}</b>
				</h6>
				<p style={{ fontSize: 'smaller' }}>
					<i>{product.information}</i>
				</p>
				<input
					className='form-control form-control-sm text-center'
					min='1'
					style={{ width: '80px' }}
					type='number'
				/>
			</div>
			<div
				className='price-delete col-1 d-flex flex-column align-items-center'
				style={{ marginLeft: '3vh' }}>
				<h6 className='mb-1'>
					<b>${product.price}</b>
				</h6>
				<i
					className='bi bi-trash3'
					style={{ cursor: 'pointer' }}></i>
			</div>
		</div>
	);
}

export default CartItem;
