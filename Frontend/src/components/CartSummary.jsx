import React from 'react';

function CartSummary() {
	return (
		<div
			className='border bg-light rounded overflow-hidden'
			style={{ padding: '1vh 1vh 0 1vh' }}>
			<div className='row row-cols-2 border-bottom p-3 mb-2'>
				<div className='col'>
					<h5>Subtotal:</h5>
				</div>
				<div className='col text-right'>
					<h6>
						<b>$TotalPrice</b>
					</h6>
				</div>
			</div>
			<div className='row row-cols-2 border-bottom p-3 mb-0'>
				<div className='col'>
					<h5>Service fee:</h5>
					<p
						className='fw-light'
						style={{ fontSize: 'small' }}>
						Picking & Packing
					</p>
				</div>
				<div className='col text-right d-flex align-items-center justify-content-end'>
					<h6>
						<b>$ServiceFeePrice</b>
					</h6>
				</div>
			</div>
			<div className='p-3'>
				<div className='row border bg-white p-3 mb-3'>
					<div className='col'>
						<h5>
							<b>Total: </b>
						</h5>
						<p
							className='fw-light mb-0'
							style={{ fontSize: 'small' }}>
							<b>GST inclusive</b>
						</p>
					</div>
					<div className='col text-right d-flex flex-column justify-content-center'>
						<h5 className='mb-0'>
							<b>$totalprice</b>
						</h5>
					</div>
				</div>
			</div>
		</div>
	);
}

export default CartSummary;
