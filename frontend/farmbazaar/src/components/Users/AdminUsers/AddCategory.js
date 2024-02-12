import React, { useState } from 'react';
import { createCategory } from '../../../services/admin.services';

const AddCategory = ({ onAdd }) => {
    const [name, setName] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await createCategory({ name });
            onAdd(response.data); // Notify parent component about the new category
            setName(''); // Clear the input field
        } catch (error) {
            console.error('Error adding category:', error);
            // Handle error
        }
    };

    return (
        <div className="container">
            <h2>Add New Category</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="name" className="form-label">Name</label>
                    <input type="text" className="form-control" id="name" value={name} onChange={(e) => setName(e.target.value)} required />
                </div>
                <button type="submit" className="btn btn-primary">Add</button>
            </form>
        </div>
    );
};

export default AddCategory;