import React, { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import Carousel from 'react-bootstrap/Carousel';
import 'bootstrap/dist/css/bootstrap.min.css';
import Spinner from 'react-bootstrap/Spinner';

//done by dionis
const ProductCatalogue = ({ keyword }) => {
	const [cartState, setCartState] = useState({}); // setState to track global produc state eg.
	// { 1: { quantity: 1, addToCart: true} }

	const [products, setProducts] = useState([]);
	const [customerId, setCustomerId] = useState(0);
	const [cartItems, setCartItems] = useState([]);
	//pagination logic
	const [currentPage, setCurrentPage] = useState(1);
	const itemsPerPage = 10;

	//fetching products for landing page
	const fetchProductLandingPage = async () => {
		try {
			const url = import.meta.env.VITE_SERVER + `api/search/landingpage`;
			const res = await fetch(url);
			if (!res.ok) {
				throw new Error('Error getting products for landing page');
			}
			const data = await res.json();
			setProducts(data);
		} catch (err) {
			console.error('Error retrieving products for LP: ', err);
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
		//to add if/else statement to check for keyword later to accomodate for search/category
		if (!keyword) {
			fetchProductLandingPage();
		} else {
		}
		const loadCart = async () => {
			const id = await fetchCustomerId();
			console.log('id: ', id);
			if (id) {
				await fetchCartItems(id);
			}
		};
		loadCart();
		console.log('keyword: ', keyword);
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

	//to 'paginate' product carousell
	const sliceProducts = (arr, size) => {
		let result = [];
		//if arr.length is 10, size = 5, it will slice
		//[ [a,b,c,d,e], [f,g,h,j] ] i = i + size
		for (let i = 0; i < arr.length; i += size) {
			let sliced = arr.slice(i, i + size); //arr.slice(0, 5), arr.slice(5, 10)
			result.push(sliced);
		}
		return result;
	};

	const productChunks = sliceProducts(products, 4);

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
				<div
					className='product-header'
					style={{ margin: '1rem 0' }}>
					<h5 style={{ color: 'darkgreen' }}>Top 10 recommended products</h5>
				</div>
			) : (
				<div
					className='category-header'
					style={{ margin: '1rem 0' }}>
					<h5 style={{ color: 'darkgreen' }}>{keyword}</h5>
					<p>{products.length} products</p>
				</div>
			)}

			{productChunks.length > 0 ? (
				<Carousel
					className='product-carousel'
					indicators={false}>
					{productChunks.map((chunk, index) => (
						<Carousel.Item
							key={index}
							interval={3000}>
							<div
								className='d-flex justify-content-center flex-wrap'
								style={{
									gap: '3rem',
									padding: '0 1rem',
								}}>
								{chunk.map((product) => (
									<Card
										key={product.id}
										style={{
											width: '16rem',
											minHeight: '365px',
											flex: '0 0 auto',
											scrollSnapAlign: 'start',
										}}>
										<Card.Img
											variant='top'
											src={`${import.meta.env.VITE_SERVER}${product.imageURL}`}
											style={{
												height: '180px',
												objectFit: 'contain',
												padding: '10px',
												cursor: 'pointer',
											}}
											onClick={() =>
												(window.location.href =
													import.meta.env.VITE_SERVER + `product/${product.id}`)
											}
										/>
										<Card.Body
											style={{
												display: 'flex',
												flexDirection: 'column',
												justifyContent: 'space-between',
												height: '100%',
											}}>
											<Card.Title
												title={product.name}
												style={{
													fontSize: '1rem',
													fontWeight: 'bold',
													marginBottom: '0.5rem',
													whiteSpace: 'normal',
													overflow: 'visible',
													textOverflow: 'unset',
												}}>
												{product.name}
											</Card.Title>

											<Card.Text style={{ marginBottom: '0.5rem' }}>
												<p
													style={{
														color: product.quantity !== 0 ? 'green' : 'red',
														margin: '0 0 4px 0',
													}}>
													{product.quantity !== 0
														? 'In Stock'
														: 'Out of Stock!'}
												</p>
												<p style={{ margin: '0 0 6px 0', fontWeight: '500' }}>
													$
													{product.price !== undefined
														? product.price.toFixed(2)
														: product.price}
												</p>
											</Card.Text>

											{product.quantity == 0 ? (
												<button
													className='btn btn-secondary'
													disabled>
													Out of Stock
												</button>
											) : //product is fetched via api and is async
											cartState[product.id]?.addToCart ? (
												<div
													class='input-group'
													style={{ maxWidth: '140px' }}>
													<button
														class='btn btn-outline-secondary'
														type='button'
														onClick={() => decreaseQuantity(product.id)}>
														−
													</button>
													<div className='quantity-input'>
														<input
															type='number'
															id='quantity'
															name='quantity'
															class='form-control text-center'
															value={cartState[product.id]?.quantity}
															min='1'
															max='30'
															required
															style={{ maxWidth: '50px' }}
															onChange={(e) =>
																handleQuantityChange(
																	e,
																	product.id,
																	product.quantity
																)
															}
														/>
													</div>

													<button
														class='btn btn-outline-secondary'
														type='button'
														onClick={() => {
															increaseQuantity(product.id, product.quantity);
															console.log('increased cartstate: ', cartState);
														}}>
														+
													</button>
												</div>
											) : (
												<Button
													onClick={() => {
														handleAddCart(product.id);
													}}
													variant='success'>
													Add to cart
												</Button>
											)}
										</Card.Body>
									</Card>
								))}
							</div>
						</Carousel.Item>
					))}
				</Carousel>
			) : (
				<Spinner
					animation='border'
					variant='success'
					role='status'>
					<span className='visually-hidden'>Loading products...</span>
				</Spinner>
			)}
		</div>
	);
};

export default ProductCatalogue;
