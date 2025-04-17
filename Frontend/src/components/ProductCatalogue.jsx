import React, { useEffect, useState } from 'react';
import Spinner from 'react-bootstrap/Spinner';
import ProductCarousel from './ProductCarousel';
import ProductGrid from './ProductGrid';
import '../index.css';

//done by dionis
const ProductCatalogue = ({ keyword, type }) => {
	const [cartState, setCartState] = useState({}); // setState to track global produc state eg.
	// { 1: { quantity: 1, addToCart: true} }

	const [products, setProducts] = useState([]);
	const [customerId, setCustomerId] = useState(0);
	const [cartItems, setCartItems] = useState([]);
	//pagination logic
	const [currentPage, setCurrentPage] = useState(1);
	const itemsPerPage = 10;

	//fetching products for landing page
	const fetchProducts = async () => {
		try {
			let url;

			if (!keyword) {
				url = import.meta.env.VITE_SERVER + `api/search/landingpage`;
			} else if (type === 'category') {
				url = import.meta.env.VITE_SERVER + `api/category/${keyword}`;
			} else if (type === 'search') {
				url = import.meta.env.VITE_SERVER + `api/search?keyword=${keyword}`;
			}
			const res = await fetch(url);
			if (!res.ok) {
				throw new Error('Error getting products');
			}
			const data = await res.json();
			setProducts(data);
		} catch (err) {
			console.error('Error retrieving products', err);
		}
	};

	//check if user is a registered user
	const fetchCustomerId = async () => {
		try {
			const url = import.meta.env.VITE_SERVER + 'api/session/customer-id';
			const res = await fetch(url);
			if (res.status === 404) return null;
			if (!res.ok) throw new Error('Customer is not a registered user');
			const data = await res.json();
			setCustomerId(data);
			//checking
			console.log('Customer ID: ', data);

			return data;
		} catch (err) {
			console.error('Error fetching customer ID: ', err);
		}
	};

	//fetch GET api to retrieve existing cart items in cart
	const fetchCartItems = async (id) => {
		try {
			const url = import.meta.env.VITE_SERVER + `api/cart/customer/${id}/items`;
			const res = await fetch(url);
			if (!res.ok) throw new Error('Error getting cart items');
			const items = await res.json();

			console.log('Cart: ', items);

			setCartItems(items);

			//updating state based on existing cart items
			const updatedState = {};

			for (let item of items) {
				updatedState[item.productId] = {
					quantity: item.quantity,
					addToCart: true,
				};
			}
			setCartState((prev) => ({
				...prev,
				...updatedState,
			}));

			return items;
		} catch (err) {
			console.error('Error fetching cart items:', err);
		}
	};

	//POST api to add item to cart
	const fetchAddToCart = async (customerId, productId) => {
		try {
			const url = import.meta.env.VITE_SERVER + 'api/cart/add';
			const res = await fetch(url, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify({
					customerId,
					productId,
				}),
			});
			if (!res.ok)
				throw new Error(`Error adding item (id: ${productId}) to cart`);
			const data = await res.json();
			return data;
		} catch (err) {
			console.error(err);
		}
	};

	const updateQuantityDB = async (productId) => {
		try {
			const item = cartItems.find((item) => item.productId === productId);
			if (!item) {
				console.warn(`Cart item not found for productId: ${productId}`);
				return;
			}
			const url = import.meta.env.VITE_SERVER + 'api/cart/update-quantity';
			const reqBody = {
				cartId: item.cartId,
				cartItemId: item.cartItemId,
				quantity: cartState[productId].quantity,
			};

			const res = await fetch(url, {
				method: 'PUT',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify(reqBody),
			});

			if (!res.ok) throw new Error('Error commiting data to db');
			const data = await res.json();
			console.log('updated to db!');
		} catch (err) {
			console.error(err);
		}
	};

	//on first render of page to fetch products to component
	useEffect(() => {
		fetchProducts();
		const loadCart = async () => {
			const id = await fetchCustomerId();
			console.log('id: ', id);
			if (id) {
				await fetchCartItems(id);
			}
		};
		loadCart();
		console.log('keyword: ', keyword);
		console.log('type: ', type);
		console.log('cartState updated from existing cart items: ', cartState);
	}, []);

	//to re-render DOM on ever change of cartState for change in quantity/addtocart etc
	useEffect(() => {
		console.log('updated cartstate: ', cartState);
		console.log('added to cart, current cart: ', cartItems);

		//updating quantity to db
		Object.entries(cartState).forEach(([productId, content]) => {
			//only if addToCart == true and quantity > 0 to update to db
			if (content.addToCart && content.quantity > 0) {
				updateQuantityDB(parseInt(productId));
			}
		});
	}, [cartState]);

	//add on button click of addtoCart
	async function handleAddCart(productId) {
		//checking if user is a registered user, else redirect to login
		if (!customerId) {
			window.location.href = import.meta.env.VITE_SERVER + `login`;
			return;
		}
		setCartState((prev) => ({
			...prev,
			[productId]: { quantity: 1, addToCart: true },
		}));
		//to call api to add to cart
		const newItems = await fetchAddToCart(customerId, productId);
		setCartItems((prev) => [...prev, newItems]);

		console.log(cartState);
	}

	//button click of + quantity logic
	function increaseQuantity(productId, stock) {
		setCartState((prev) => {
			const currentQuantity = prev[productId]?.quantity;
			//soft validation to prevent user from selecting more than 30 or more than product stock qty
			if (currentQuantity >= 30 || currentQuantity >= stock) return prev;

			// { 1 : {quantity: 1, addTocart: true}, 2: {quantity: 1, addToCart:true}}
			const updated = {
				...prev,
				[productId]: {
					...prev[productId],
					quantity: currentQuantity + 1,
				},
			};

			return updated;
		});
	}

	function decreaseQuantity(productId) {
		setCartState((prev) => {
			const currentQuantity = prev[productId]?.quantity;
			// { 1 : {quantity: 1, addTocart: true}, 2: {quantity: 1, addToCart:true}}
			const updated = {
				...prev,
				[productId]: {
					...prev[productId],
					quantity: currentQuantity <= 1 ? 0 : currentQuantity - 1,
					addToCart: currentQuantity <= 1 ? false : true,
				},
			};

			return updated;
		});
	}

	function handleQuantityChange(e, productId, stock) {
		const newQuantity = parseInt(e.target.value);
		//applying clamping
		const validQuant = Math.max(1, Math.min(newQuantity, stock, 30));

		setCartState((prev) => {
			const updated = {
				...prev,
				[productId]: {
					...prev[productId],
					quantity: validQuant,
				},
			};

			return updated;
		});
	}

	return (
		<div className='container mt-4'>
			<style>
				{`
					.product-carousel {
						position: relative;
				
					}

					.product-carousel .carousel-control-prev,
					.product-carousel .carousel-control-next {
						top: 50%;
						transform: translateY(-50%);
						width: 5%;
					
					}

					.product-carousel .carousel-control-prev {
						left: 0;
					}

					.product-carousel .carousel-control-next {
						right: 0;
					}

					.product-carousel .carousel-control-prev-icon,
					.product-carousel .carousel-control-next-icon {
						background-color: rgba(0, 0, 0, 0.5);
						border-radius: 50%;
						padding: 10px;
					}

					.product-carousel .carousel-control-prev,
					.product-carousel .carousel-control-next {
						opacity: 0;
						transition: opacity 0.3s ease;
					}

					.product-carousel:hover .carousel-control-prev,
					.product-carousel:hover .carousel-control-next {
					
						opacity: 1;
						
					}

					.out-of-stock-btn {
						cursor: not-allowed !important;
					}

					.quantity-input input[type=number]::-webkit-inner-spin-button,
					.quantity-input input[type=number]::-webkit-outer-spin-button {
						-webkit-appearance: none;
						margin: 0;
					}

					.quantity-input  input[type=number] {
						-moz-appearance: textfield;
					}

				
				`}
			</style>
			{!keyword ? (
				<ProductCarousel
					products={products}
					cartState={cartState}
					handleAddCart={handleAddCart}
					increaseQuantity={increaseQuantity}
					decreaseQuantity={decreaseQuantity}
					handleQuantityChange={handleQuantityChange}
				/>
			) : (
				<ProductGrid
					products={products}
					cartState={cartState}
					keyword={keyword}
					handleAddCart={handleAddCart}
					increaseQuantity={increaseQuantity}
					decreaseQuantity={decreaseQuantity}
					handleQuantityChange={handleQuantityChange}
					currentPage={currentPage}
					setCurrentPage={setCurrentPage}
					itemsPerPage={itemsPerPage}
					type={type}
					cartItems={cartItems}
					setCartItems={setCartItems}
				/>
			)}
		</div>
	);
};

export default ProductCatalogue;
