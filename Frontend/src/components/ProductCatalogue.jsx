import React, { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import Carousel from 'react-bootstrap/Carousel';
import 'bootstrap/dist/css/bootstrap.min.css';
import Spinner from 'react-bootstrap/Spinner';

const ProductCatalogue = ({ keyword }) => {
	const [cartState, setCartState] = useState({}); // setState to track global produc state eg.
	// { 1: {
	// quantity: 1,
	// addToCart: true},
	//             }
	const [itemQuant, setItemQuant] = useState(1);
	const [products, setProducts] = useState([]);

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
	//on first render of page to fetch products to component
	useEffect(() => {
		//to add if/else statement to check for keyword later to accomodate for search/category
		fetchProductLandingPage();
	}, []);

	useEffect(() => {
		console.log('updated cartstate: ', cartState);
	}, [cartState]);

	//add on button click of addtoCart
	function handleAddCart(productId) {
		setCartState((prev) => ({
			...prev,
			[productId]: { quantity: 1, addToCart: true },
		}));
		//to call api to add to cart

		console.log(cartState);
	}

	//button click of + quantity logic
	function increaseQuantity(productId, stock) {
		setCartState((prev) => {
			const currentQuantity = prev[productId]?.quantity;
			//soft validation to prevent user from selecting more than 30 or more than product stock qty
			if (currentQuantity >= 30 || currentQuantity >= stock) return prev;

			// { 1 : {quantity: 1, addTocart: true}, 2: {quantity: 1, addToCart:true}}
			return {
				...prev,
				[productId]: {
					...prev[productId],
					quantity: currentQuantity + 1,
				},
			};
		});
	}

	function decreaseQuantity(productId) {
		setCartState((prev) => {
			const currentQuantity = prev[productId]?.quantity;
			if (currentQuantity <= 1) {
				// { 1 : {quantity: 1, addTocart: true}, 2: {quantity: 1, addToCart:true}}
				return {
					...prev,
					[productId]: {
						...prev[productId],
						quantity: 0,
						addToCart: false,
					},
				};
			} else {
				return {
					...prev,
					[productId]: {
						...prev[productId],
						quantity: currentQuantity - 1,
					},
				};
			}
		});
	}

	function handleItemQuant(e) {
		const quant = parseInt(e.target.value);
		if (quant === 0) setIsAddCart(false);
		setItemQuant(quant);
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

	const productChunks = sliceProducts(products, 5);

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

				
				`}
			</style>

			{productChunks.length > 0 ? (
				<Carousel
					className='product-carousel'
					indicators={false}>
					{productChunks.map((chunk, index) => (
						<Carousel.Item
							key={index}
							interval={3000}>
							<div className='d-flex justify-content-around w-100 overflow-hidden'>
								{chunk.map((product) => (
									<Card
										key={product.id}
										style={{
											width: '16rem',
											height: '100%',
											minHeight: '365px',
											flex: '0 0 auto',
											margin: '0 0.5rem',
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
											{/* productId is fetched by API and is async */}
											{cartState[product.id]?.addToCart ? (
												<div
													class='input-group'
													style={{ maxWidth: '140px' }}>
													<button
														class='btn btn-outline-secondary'
														type='button'
														onClick={() => decreaseQuantity(product.id)}>
														−
													</button>
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
													/>
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
