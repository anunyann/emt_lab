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
import useHosts from '../../hooks/useHosts';

const CATEGORIES = ['ROOM', 'HOUSE', 'FLAT', 'APARTMENT', 'HOTEL', 'MOTEL'];

const AccommodationForm = ({ open, handleClose, accommodation, onSubmit }) => {
    const { hosts } = useHosts();
    const [formData, setFormData] = useState({
        name: '',
        category: 'ROOM',
        hostId: '',
        numRooms: 1
    });

    useEffect(() => {
        if (accommodation) {
            setFormData({
                name: accommodation.name,
                category: accommodation.category,
                hostId: accommodation.host?.id || '',
                numRooms: accommodation.numRooms
            });
        }
    }, [accommodation]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: name === 'numRooms' ? Number(value) : value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(formData);
        handleClose();
    };

    return (
        <Dialog open={open} onClose={handleClose} maxWidth="sm" fullWidth>
            <DialogTitle>{accommodation ? 'Edit Accommodation' : 'Add New Accommodation'}</DialogTitle>
            <form onSubmit={handleSubmit}>
                <DialogContent>
                    <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
                        <TextField
                            name="name"
                            label="Name"
                            fullWidth
                            value={formData.name}
                            onChange={handleChange}
                            required
                        />

                        <FormControl fullWidth required>
                            <InputLabel>Category</InputLabel>
                            <Select
                                name="category"
                                value={formData.category}
                                onChange={handleChange}
                                label="Category"
                            >
                                {CATEGORIES.map(category => (
                                    <MenuItem key={category} value={category}>
                                        {category}
                                    </MenuItem>
                                ))}
                            </Select>
                        </FormControl>

                        <FormControl fullWidth required>
                            <InputLabel>Host</InputLabel>
                            <Select
                                name="hostId"
                                value={formData.hostId}
                                onChange={handleChange}
                                label="Host"
                            >
                                {hosts.map(host => (
                                    <MenuItem key={host.id} value={host.id}>
                                        {host.name} {host.surname} - {host.country?.name}
                                    </MenuItem>
                                ))}
                            </Select>
                        </FormControl>

                        <TextField
                            name="numRooms"
                            label="Number of Rooms"
                            type="number"
                            fullWidth
                            value={formData.numRooms}
                            onChange={handleChange}
                            InputProps={{ inputProps: { min: 1 } }}
                            required
                        />
                    </Box>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button type="submit" variant="contained" color="primary">
                        {accommodation ? 'Update' : 'Create'}
                    </Button>
                </DialogActions>
            </form>
        </Dialog>
    );
};

export default AccommodationForm;