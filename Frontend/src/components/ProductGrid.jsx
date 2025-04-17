import React from 'react';
import ProductCard from './ProductCard';
import Pagination from 'react-bootstrap/Pagination';

//done by dionis
const ProductGrid = ({
	products,
	cartState,
	keyword,
	handleAddCart,
	increaseQuantity,
	decreaseQuantity,
	handleQuantityChange,
	currentPage,
	setCurrentPage,
	itemsPerPage,
	type,
	cartItems,
	setCartItems,
}) => {
	//pagination logic for search/category pages

	//generate page numbers for pagination
	const pageNumbers = [];

	return (
		<div>
			<div className='productgrid-header'>
				<h4 style={{ color: 'darkgreen' }}>{keyword}</h4>
				<p>{products.length} products</p>
			</div>
			<div className='row row-cols-2 row-cols-md-5 row-cols-sm-3 g-4'>
				{products.map((product) => (
					<div
						className='col'
						key={product.id}>
						<ProductCard
							product={product}
							cartState={cartState}
							handleAddCart={handleAddCart}
							increaseQuantity={increaseQuantity}
							decreaseQuantity={decreaseQuantity}
							handleQuantityChange={handleQuantityChange}
						/>
					</div>
				))}
			</div>

			{/* pagination navigation */}
			<Pagination className='mt-4 justify-content-center'>
				{pageNumbers.map((number) => (
					<Pagination.Item
						key={number}
						active={number === currentPage}
						onClick={() => setCurrentPage(number)}>
						{number}
					</Pagination.Item>
				))}
			</Pagination>
		</div>
	);
};

export default ProductGrid;
