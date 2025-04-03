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
							<Card.Title>Card Title</Card.Title>
							<Card.Text>
								Some quick example text to build on the card title and make up
								the bulk of the card's content.
							</Card.Text>
							<Button variant='primary'>Go somewhere</Button>
						</Card.Body>
					</Card>
				</div>
			</div>
		</div>
	);
};

export default Product;
