import React from 'react';
import Button from 'react-bootstrap/Button';

function CartSummary({ subtotal, serviceFee, total }) {
	return (
		<div
			className='border bg-light rounded d-flex flex-column justify-content-between'
			style={{ padding: '1vh', minHeight: '60%' }}>
			<div>
				<div className='d-flex justify-content-between border-bottom p-3 mb-2'>
					<h5>Subtotal:</h5>
					<h6>
						<b>${subtotal.toFixed(2)}</b>
					</h6>
				</div>

				<div className='d-flex justify-content-between border-bottom p-3 mb-0'>
					<div>
						<h5>Service fee:</h5>
						<p
							className='fw-light'
							style={{ fontSize: 'small' }}>
							Picking & Packing
						</p>
					</div>
					<h6 className='d-flex align-items-center'>
						<b>${serviceFee.toFixed(2)}</b>
					</h6>
				</div>
				<div className='p-3'>
					<div className='row border bg-white p-3'>
						<div className='col'>
							<h5>
								<b>Total: </b>
							</h5>
						</div>
						<div className='col text-end d-flex flex-column justify-content-center'>
							<h5 className='mb-0'>
								<b>${total}</b>
							</h5>
						</div>
					</div>
				</div>
			</div>
		</div>
	);
}

export default CartSummary;
