import React from 'react';
import Carousel from 'react-bootstrap/Carousel';
import Card from 'react-bootstrap/Card';
import ProductCard from './ProductCard';

//done by dionis
const ProductCarousel = ({
	products,
	cartState,
	handleAddCart,
	increaseQuantity,
	decreaseQuantity,
	handleQuantityChange,
}) => {
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
		<>
			<div>
				<h5 style={{ color: 'darkgreen', margin: '2rem 0 1rem 0' }}>
					Top 10 recommended products
				</h5>
			</div>
			<Carousel
				className='product-carousel'
				indicators={false}>
				{productChunks.map((chunk, index) => (
					<Carousel.Item
						key={index}
						interval={3000}>
						<div
							className='d-flex justify-content-center flex-wrap'
							style={{ gap: '3rem', padding: '0 1rem' }}>
							{chunk.map((product) => (
								<ProductCard
									key={product.id}
									product={product}
									cartState={cartState}
									handleAddCart={handleAddCart}
									increaseQuantity={increaseQuantity}
									decreaseQuantity={decreaseQuantity}
									handleQuantityChange={handleQuantityChange}
								/>
							))}
						</div>
					</Carousel.Item>
				))}
			</Carousel>
		</>
	);
};

export default ProductCarousel;
