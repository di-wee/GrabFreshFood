import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';

//this react component is REUSABLE and will be used for both landing page and search/categories etc.

const ProductCatalogue = ({ keyword }) => {
	const [isAddCart, setIsAddCart] = useState(false);
	const [itemQuant, setItemQuant] = useState(1);

	//call GET REST api to retrieve all products into an array.
	//if keyword is null (eg. this is for landing page),
	// retrieve mayb first 10, but backend rest api must configure or cater

	//else. if keyword NOT null, meaning this component is being used for a search/categories
	//call GET REST api to retrieve products based on query param eg '/search?category={keyword}',

	//after retrieving, use state management to store your data and then
	// map your products details to the respective div

	function handleAddCart() {
		//add to cart logic goes here
		//call a POST API to add item to cart.

		//to toggle visibility
		setIsAddCart(true);
	}

	//to handle functionality of increasing/decreasing item quantity
	//u might have to reconfigure the useState for item quantity to accomodate to an array
	//and change the logic accordingly
	function handleItemQuant(e) {
		const quant = parseInt(e.target.value);

		//if quantity === 0, then it will set boolean flag isAddCart to false to trigger visibility
		if (quant === 0) {
			setIsAddCart(false);
			console.log('isAddCart: ' + isAddCart);
			console.log('itemQuant: ' + quant);
		}
		setItemQuant(quant);
	}

	function handleProductOnClick() {
		//this will need to redirect to Priscilla's spring controller, make sure to
		// include the id of the product to accomodate for her logic

	}

	return (
		<div className='container'>
			<div className='row justify-content-center align-items-center'>
				<div className='col-md-4'>
					<Card>
						<Card.Img
							variant='top'
							src='https://via.placeholder.com/150'
						/>
						<Card.Body style={{ width: '250vh' }}>
							<Card.Title>Product Title</Card.Title>
							<Card.Text>Product Description</Card.Text>
							<Card.Text>$ProductPrice</Card.Text>
							{isAddCart ? (
								<input
									type='number'
									min={0}
									value={itemQuant}
									onChange={(e) => handleItemQuant(e)}></input>
							) : (
								<Button
									onClick={handleAddCart}
									variant='success'>
									Add to cart
								</Button>
							)}
						</Card.Body>
					</Card>
				</div>
			</div>
		</div>
	);
};

export default ProductCatalogue;
