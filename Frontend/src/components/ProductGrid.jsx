import React from 'react';
import ProductCard from './ProductCard';
import Pagination from 'react-bootstrap/Pagination';

//done by dionis
const ProductGrid = ({
	products,
	cartState,
	handleAddCart,
	increaseQuantity,
	decreaseQuantity,
	handleQuantityChange,
	currentPage,
	itemsPerPage,
	setCurrentPage,
	type,
}) => {
	//pagination logic for search/category pages
	const indexOfLastItem = currentPage * itemsPerPage;
	const indexOfFirstItem = indexOfLastItem - itemsPerPage;
	const currentItems = products.slice(indexOfFirstItem, indexOfLastItem);

	const totalPages = Math.ceil(products.length / itemsPerPage);

	//generate page numbers for pagination
	const pageNumbers = [];
	for (let i = 1; i <= totalPages; i++) {
		pageNumbers.push(i);
	}

	return (
		<div>
			{/* product grid display */}
			<div className='row row-cols-2 row-cols-md-5 row-cols-sm-3 g-4'>
				{currentItems.map((product) => (
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
