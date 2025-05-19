import React, { useState, useEffect } from 'react';
import {
    Button,
    TextField,
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    FormControl,
    InputLabel,
    Select,
    MenuItem,
    Box
} from '@mui/material';
import useCountries from '../../hooks/useCountries';

const HostForm = ({ open, handleClose, host, onSubmit }) => {
    const { countries } = useCountries();
    const [formData, setFormData] = useState({
        name: '',
        surname: '',
        countryId: ''
    });

    useEffect(() => {
        if (host) {
            setFormData({
                name: host.name,
                surname: host.surname,
                countryId: host.country?.id || ''
            });
        }
    }, [host]);

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
            <DialogTitle>{host ? 'Edit Host' : 'Add New Host'}</DialogTitle>
            <form onSubmit={handleSubmit}>
                <DialogContent>
                    <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
                        <TextField
                            name="name"
                            label="First Name"
                            fullWidth
                            value={formData.name}
                            onChange={handleChange}
                            required
                        />

                        <TextField
                            name="surname"
                            label="Last Name"
                            fullWidth
                            value={formData.surname}
                            onChange={handleChange}
                            required
                        />

                        <FormControl fullWidth required>
                            <InputLabel>Country</InputLabel>
                            <Select
                                name="countryId"
                                value={formData.countryId}
                                onChange={handleChange}
                                label="Country"
                            >
                                {countries.map(country => (
                                    <MenuItem key={country.id} value={country.id}>
                                        {country.name} - {country.continent}
                                    </MenuItem>
                                ))}
                            </Select>
                        </FormControl>
                    </Box>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button type="submit" variant="contained" color="primary">
                        {host ? 'Update' : 'Create'}
                    </Button>
                </DialogActions>
            </form>
        </Dialog>
    );
};

export default HostForm;