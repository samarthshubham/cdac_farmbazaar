import React, { useState, useEffect } from 'react';
import { getAllFarmerUsers, getAllProducts, assignProductsToFarmer } from '../../../services/admin.services';

const AssignProducts = () => {
    const [farmers, setFarmers] = useState([]);
    const [products, setProducts] = useState([]);
    const [selectedFarmer, setSelectedFarmer] = useState('');
    const [selectedProducts, setSelectedProducts] = useState([]);

    useEffect(() => {
        const fetchFarmers = async () => {
            try {
                const response = await getAllFarmerUsers();
                setFarmers(response.data);
            } catch (error) {
                console.error('Error fetching farmers:', error);
            }
        };

        const fetchProducts = async () => {
            try {
                const response = await getAllProducts();
                setProducts(response.data);
            } catch (error) {
                console.error('Error fetching products:', error);
            }
        };

        fetchFarmers();
        fetchProducts();
    }, []);

    const handleAssignProducts = async () => {
        try {
            await assignProductsToFarmer(selectedFarmer, selectedProducts);
            console.log('Products assigned successfully.');
            window.alert('Products assigned successfully.'); // Show a popup
            // You may want to perform additional actions after assigning the products
        } catch (error) {
            console.error('Error assigning products to farmer:', error);
        }
    };
    

    return (
        <div className="container mt-5">
            <h2 className="mb-4">Assign Products to Farmer</h2>
            <div className="row">
                <div className="col-md-6">
                    <label htmlFor="selectFarmer" className="form-label">Select Farmer:</label>
                    <select id="selectFarmer" className="form-select mb-3" value={selectedFarmer} onChange={(e) => setSelectedFarmer(e.target.value)}>
                        <option value="">Select Farmer</option>
                        {farmers.map(farmer => (
                            <option key={farmer.id} value={farmer.id}>{farmer.fname} {farmer.lname}</option>
                        ))}
                    </select>
                </div>
                <div className="col-md-6">
                    <label htmlFor="selectProducts" className="form-label">Select Products:</label>
                    <select id="selectProducts" className="form-select mb-3" multiple value={selectedProducts} onChange={(e) => setSelectedProducts(Array.from(e.target.selectedOptions, option => option.value))}>
                        {products.map(product => (
                            <option key={product.id} value={product.id}>{product.name}</option>
                        ))}
                    </select>
                </div>
            </div>
            <div className="row">
                <div className="col-md-12">
                    <button className="btn btn-primary" onClick={handleAssignProducts}>Assign Products</button>
                </div>
            </div>
        </div>
    );
};

export default AssignProducts;