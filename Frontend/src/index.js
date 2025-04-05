import ReactDOM from 'react-dom/client';
import ProductCatalogue from './components/ProductCatalogue';
import ShoppingCart from './components/ShoppingCart';

const rootEl = document.getElementById('react-catalogue');
if (rootEl) {
	ReactDOM.createRoot(rootEl).render(<ProductCatalogue />);
}

const cartEl = document.getElementById('react-cart');
if (cartEl) {
	ReactDOM.createRoot(cartEl).render(<ShoppingCart />);
}
