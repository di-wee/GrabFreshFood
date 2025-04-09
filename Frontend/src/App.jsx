import React from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import ShoppingCart from './components/ShoppingCart';
import ProductCatalogue from './components/Productcatalogue';
const App = () => {
	//logic to retrieve and call GET API to retrieve Products
	// afterwards to loop and prop down to Product card

	return (
		<>
			<ProductCatalogue />
		</>
	);
};

export default App;
