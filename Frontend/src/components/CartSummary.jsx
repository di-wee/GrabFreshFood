import React from 'react';

function CartSummary({ subtotal, serviceFee, total, selectedCount }) {
	return (
		<div className="card shadow-sm border-0">
			<div className="card-body p-4">
				<div className="d-flex justify-content-between align-items-center mb-4">
					<h5 className="mb-0">Order Summary</h5>
					<span className="badge bg-secondary">
						{selectedCount} {selectedCount === 1 ? 'item' : 'items'}
					</span>
				</div>

				<div className="d-flex justify-content-between mb-3">
					<span className="text-muted">Subtotal</span>
					<span className="fw-semibold">${subtotal.toFixed(2)}</span>
				</div>

				<div className="d-flex justify-content-between align-items-start mb-3">
					<div>
						<span className="text-muted">Service fee</span>
						<div className="small text-muted">Picking & Packing</div>
					</div>
					<span className="fw-semibold">${serviceFee.toFixed(2)}</span>
				</div>

				<hr />

				<div className="d-flex justify-content-between mt-3">
					<h5>Total</h5>
					<h5 className="fw-bold">${total}</h5>
				</div>
			</div>
		</div>
	);
}

export default CartSummary;
