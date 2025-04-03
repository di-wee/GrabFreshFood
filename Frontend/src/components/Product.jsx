import React from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';

const Product = () => {
	return (
		<div className='container'>
			<div
				className='row justify-content-center align-items-center'
				style={{ height: '100vh' }} // full screen height
			>
				<div className='col-md-4'>
					<Card>
						<Card.Img
							variant='top'
							src='https://via.placeholder.com/150'
						/>
						<Card.Body>
							<Card.Title>Product Title</Card.Title>
							<Card.Text>Product Description</Card.Text>
							<Button variant='primary'>Add to Cart</Button>
						</Card.Body>
					</Card>
				</div>
			</div>
		</div>
	);
};

export default Product;
