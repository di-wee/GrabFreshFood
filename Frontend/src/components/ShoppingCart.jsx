import React from 'react';
import CartItem from './CartItem';
import CartSummary from './CartSummary';

function ShoppingCart() {
	return (
		<div className='main container'>
			<div className='title row'>
				<div className='col'>
					<h5>Shopping Cart</h5>
				</div>
				<div className='col text-right'>
					<button className='btn btn-outline-danger'>
						<span style={{ marginRight: '5px' }}>
							<i className='bi bi-trash3'></i>
						</span>
						Empty cart
					</button>
				</div>
			</div>

			<div className='container'>
				<form>
					<div className='row mt-4'>
						{/* Shopping cart product grid */}
						<div className='col-md-7 pr-md-3'>
							<div
								className='border bg-light rounded overflow-hidden'
								style={{ padding: '1vh 1vh 0 1vh' }}>
								<CartItem />
							</div>
						</div>

						{/* Subtotal grid */}
						<div className='col-md-5 pl-md-3'>
							<CartSummary />
							<div className='text-right mt-3'>
								<button
									className='btn btn-success btn-sm'
									style={{ width: '100%' }}
									type='submit'>
									Checkout
								</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	);
}

export default ShoppingCart;
