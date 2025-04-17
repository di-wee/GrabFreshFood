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

	//getting number of pages (dynamic based on existing producs.length)
	const totalPages = Math.ceil(products.length / itemsPerPage);

	//generate page numbers for pagination
	const pageNumbers = [];
	for (let i = 1; i <= totalPages; i++) {
		pageNumbers.push(i);
	}

	//slicing up arrays to show items in each page
	const endIdx = currentPage * itemsPerPage; // page1 is 10, page2 is 20
	const startIdx = endIdx - itemsPerPage; //  page1 is 0, page2 is 10
	const currentItems = products.slice(startIdx, endIdx); //

	//on click logic of previous page
	const handlePreviousPage = (e) => {
		e.preventDefault();
		if (currentPage > 1) setCurrentPage(currentPage - 1);
	};

	//on click logic for next page
	const handleNextPage = (e) => {
		e.preventDefault();
		if (currentPage < totalPages) setCurrentPage(currentPage + 1);
	};

	return (
		<div
			style={{ minHeight: '140vh', display: 'flex', flexDirection: 'column' }}>
			<style>
				{`
                    .product-grid {
                        row-gap: 2rem;
                        column-gap: 2rem;
                    }

                    .product-grid > col
                        display: flex;
                        justify-content: center;
                    
                    `}
			</style>
			<div className='productgrid-header'>
				{type === 'category' ? (
					<>
						<h4 style={{ color: 'darkgreen' }}>{keyword}</h4>
						<p>{products.length} products</p>
					</>
				) : (
					<>
						<h4 style={{ color: 'darkgreen' }}>Results for "{keyword}"</h4>
						<p>{products.length} products</p>
					</>
				)}
			</div>
			<div className='row row-cols-2 row-cols-md-5 row-cols-sm-3 product-grid'>
				{/* mapping via the sliced array for pagination */}
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

			<nav
				aria-label='page-navigation'
				className='mt-5'>
				<ul class='pagination justify-content-center'>
					<li class={`page-item ${currentPage === 1 ? 'disabled' : ''}`}>
						<a
							class='page-link'
							href='#'
							aria-label='Previous'
							onClick={(e) => handlePreviousPage(e)}>
							<span aria-hidden='true'>&laquo;</span>
						</a>
					</li>
					{pageNumbers.map((number) => (
						<li
							key={number}
							class={`page-item ${number === currentPage ? 'active' : ''}`}>
							<a
								class='page-link'
								href='#'
								onClick={(e) => {
									e.preventDefault();
									setCurrentPage(number);
								}}>
								{number}
							</a>
						</li>
					))}

					<li
						class={`page-item ${currentPage === totalPages ? 'disabled' : ''}`}>
						<a
							class='page-link'
							href='#'
							aria-label='Next'
							onClick={(e) => handleNextPage(e)}>
							<span aria-hidden='true'>&raquo;</span>
						</a>
					</li>
				</ul>
			</nav>
		</div>
	);
};

export default ProductGrid;
