import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import './index.css';
import App from './App.jsx';
import ShoppingCart from './components/ShoppingCart';
import ProductCatalogue from './components/ProductCatalogue.jsx';
import 'bootstrap/dist/css/bootstrap.min.css';

const rootEl = document.getElementById('root');
if (rootEl) {
	createRoot(rootEl).render(
		<StrictMode>
			<App />
		</StrictMode>
	);
}

const cartEl = document.getElementById('react-cart');
if (cartEl) {
	createRoot(cartEl).render(
		<StrictMode>
			<ShoppingCart />
		</StrictMode>
	);
}

const catalogueEl = document.getElementById('react-catalogue');
if (catalogueEl) {
	createRoot(catalogueEl).render(
		<StrictMode>
			<ProductCatalogue />
		</StrictMode>
	);
}
