import React, { useState, useEffect } from 'react';
import {
    Button,
    TextField,
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    Box
} from '@mui/material';

const CountryForm = ({ open, handleClose, country, onSubmit }) => {
    const [formData, setFormData] = useState({
        name: '',
        continent: ''
    });
    
    useEffect(() => {
        if (country) {
            setFormData({
                name: country.name,
                continent: country.continent
            });
        }
    }, [country]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(formData);
        handleClose();
    };

    return (
        <Dialog open={open} onClose={handleClose} maxWidth="sm" fullWidth>
            <DialogTitle>{country ? 'Edit Country' : 'Add New Country'}</DialogTitle>
            <form onSubmit={handleSubmit}>
                <DialogContent>
                    <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
                        <TextField
                            name="name"
                            label="Country Name"
                            fullWidth
                            value={formData.name}
                            onChange={handleChange}
                            required
                        />

                        <TextField
                            name="continent"
                            label="Continent"
                            fullWidth
                            value={formData.continent}
                            onChange={handleChange}
                            required
                        />
                    </Box>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button type="submit" variant="contained" color="primary">
                        {country ? 'Update' : 'Create'}
                    </Button>
                </DialogActions>
            </form>
        </Dialog>
    );
};

export default CountryForm;