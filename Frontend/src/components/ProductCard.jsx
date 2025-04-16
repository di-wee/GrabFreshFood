import React from 'react';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';

//done by dionis
const ProductCard = ({
	product,
	cartState,
	handleAddCart,
	increaseQuantity,
	decreaseQuantity,
	handleQuantityChange,
}) => {
	return (
		<Card
			key={product.id}
			style={{
				width: '16rem',
				minHeight: '365px',
				flex: '0 0 auto',
				scrollSnapAlign: 'start',
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
						{product.quantity !== 0 ? 'In Stock' : 'Out of Stock!'}
					</p>
					<p style={{ margin: '0 0 6px 0', fontWeight: '500' }}>
						$
						{product.price !== undefined
							? product.price.toFixed(2)
							: product.price}
					</p>
				</Card.Text>

				{/* product is fetched via api and is async */}
				{product.quantity == 0 ? (
					<button
						className='btn btn-secondary'
						disabled>
						Out of Stock
					</button>
				) : cartState[product.id]?.addToCart ? (
					<div
						class='input-group'
						style={{ maxWidth: '140px' }}>
						<button
							class='btn btn-outline-secondary'
							type='button'
							onClick={() => decreaseQuantity(product.id)}>
							−
						</button>
						<div className='quantity-input'>
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
								onChange={(e) =>
									handleQuantityChange(e, product.id, product.quantity)
								}
							/>
						</div>
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
	);
};

export default ProductCard;
