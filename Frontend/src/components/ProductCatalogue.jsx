import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';

const ProductCatalogue = () => {
	const [isAddCart, setIsAddCart] = useState(false);
	const [itemQuant, setItemQuant] = useState(1);

	function handleAddCart() {
		//add to cart logic goes here
		//REST api to GET product information and then to apply .map to each product card

		//to toggle visibility
		setIsAddCart(true);
	}

	//to handle functionality of increasing/decreasing item quantity
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
