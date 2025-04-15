import React, { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import Carousel from 'react-bootstrap/Carousel';
import 'bootstrap/dist/css/bootstrap.min.css';

const ProductCatalogue = ({ keyword }) => {
	const [isAddCart, setIsAddCart] = useState(false);
	const [itemQuant, setItemQuant] = useState(1);
	const [products, setProducts] = useState([]);

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

	useEffect(() => {
		fetchProductLandingPage();
	}, []);

	function handleAddCart() {
		setIsAddCart(true);
	}

	function handleItemQuant(e) {
		const quant = parseInt(e.target.value);
		if (quant === 0) setIsAddCart(false);
		setItemQuant(quant);
	}

	function handleProductOnClick() {
		// redirect to spring product page
	}

	//to 'paginate' product carousell
	const sliceProducts = (arr, size) => {
		let result = [];
		//if arr.length is 10, size = 5, it will slice
		//[ [a,b,c,d,e], [f,g,h,j] ]
		for (let i = 0; i < arr.length; i += size) {
			result.push(arr.slice(i, i + size));
		}
		return result;
	};

	const productChunks = sliceProducts(products, 5);

	return (
		<div className='container mt-4'>
			<style>
				{`
					.carousel-control-prev,
					.carousel-control-next {
						opacity: 1 !important;
					}
					.carousel-control-prev-icon,
					.carousel-control-next-icon {
						background-color: rgba(0, 0, 0, 0.5);
						border-radius: 50%;
						padding: 10px;
						
					}
				`}
			</style>

			{productChunks.length > 0 ? (
				<Carousel indicators={false}>
					{productChunks.map((chunk, index) => (
						<Carousel.Item
							key={index}
							interval={1500}>
							<div className='d-flex justify-content-center flex-wrap gap-3'>
								{chunk.map((product) => (
									<Card
										key={product.id}
										style={{ width: '14rem', flex: '1 0 auto' }}>
										<Card.Img
											variant='top'
											src='https://via.placeholder.com/150'
										/>
										<Card.Body>
											<Card.Title title={product.name}>
												{product.name}
											</Card.Title>

											<Card.Text>
												$
												{product.price !== undefined
													? product.price.toFixed(2)
													: product.price}
											</Card.Text>
											{isAddCart ? (
												<input
													type='number'
													min={0}
													value={itemQuant}
													onChange={(e) => handleItemQuant(e)}
													className='form-control'
												/>
											) : (
												<Button
													onClick={handleAddCart}
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
				<p>Loading products...</p>
			)}
		</div>
	);
};

export default ProductCatalogue;
