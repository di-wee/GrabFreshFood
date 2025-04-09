import React, { useEffect, useState } from 'react';

function CartItem({ item }) {
	const [product, setProduct] = useState({});

	const fetchProduct = async () => {
		try {
			const url = import.meta.env.VITE_SERVER + `api/product/${item.productId}`;

			const res = await fetch(url);
			if (!res.ok) throw new Error('Error getting product details!');
			const data = await res.json();
			console.log('Product: ', data);
			setProduct(data);
			return data;
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
					<b>Product Name</b>
				</h6>
				<p>
					unit g <span>·</span> <span>$Price</span>
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
					<b>$Price</b>
				</h6>
				<i
					className='bi bi-trash3'
					style={{ cursor: 'pointer' }}></i>
			</div>
		</div>
	);
}

export default CartItem;
