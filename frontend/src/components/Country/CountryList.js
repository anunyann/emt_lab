import React, { useState } from 'react';
import {
    Typography,
    Button,
    Paper,
    Box,
    IconButton,
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    Grid,
    Chip
} from '@mui/material';
import { Edit as EditIcon, Delete as DeleteIcon, Public as PublicIcon } from '@mui/icons-material';
import CountryForm from './CountryForm';
import useCountries from '../../hooks/useCountries';

const getContinent = (continent) => {
    const continents = {
        'Europe': 'primary',
        'North America': 'secondary',
        'South America': 'success',
        'Asia': 'warning',
        'Africa': 'error',
        'Australia': 'info',
        'Antarctica': 'default'
    };

    return continents[continent] || 'default';
};

const CountryList = () => {
    const { countries, createCountry, updateCountry, deleteCountry } = useCountries();

    const [openAddDialog, setOpenAddDialog] = useState(false);
    const [openEditDialog, setOpenEditDialog] = useState(false);
    const [openDeleteDialog, setOpenDeleteDialog] = useState(false);
    const [selectedCountry, setSelectedCountry] = useState(null);

    const handleOpenEdit = (country) => {
        setSelectedCountry(country);
        setOpenEditDialog(true);
    };

    const handleOpenDelete = (country) => {
        setSelectedCountry(country);
        setOpenDeleteDialog(true);
    };

    const handleCreate = (formData) => {
        createCountry(formData);
    };

    const handleUpdate = (formData) => {
        if (selectedCountry) {
            updateCountry(selectedCountry.id, formData);
        }
    };

    const handleDelete = () => {
        if (selectedCountry) {
            deleteCountry(selectedCountry.id);
            setOpenDeleteDialog(false);
        }
    };

    return (
        <Box>
            <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 3 }}>
                <Typography variant="h5">Countries</Typography>
                <Button
                    variant="contained"
                    color="primary"
                    onClick={() => setOpenAddDialog(true)}
                >
                    Add Country
                </Button>
            </Box>

            <Grid container spacing={2}>
                {countries.map(country => (
                    <Grid item xs={12} sm={6} md={4} key={country.id}>
                        <Paper sx={{ p: 2, height: '100%', display: 'flex', flexDirection: 'column' }}>
                            <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 2 }}>
                                <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
                                    <PublicIcon color="primary" />
                                    <Typography variant="h6">
                                        {country.name}
                                    </Typography>
                                </Box>
                                <Box>
                                    <IconButton
                                        size="small"
                                        color="primary"
                                        onClick={() => handleOpenEdit(country)}
                                    >
                                        <EditIcon />
                                    </IconButton>
                                    <IconButton
                                        size="small"
                                        color="error"
                                        onClick={() => handleOpenDelete(country)}
                                    >
                                        <DeleteIcon />
                                    </IconButton>
                                </Box>
                            </Box>

                            <Box sx={{ mt: 1 }}>
                                <Chip
                                    label={country.continent}
                                    color={getContinent(country.continent)}
                                    size="small"
                                />
                            </Box>
                        </Paper>
                    </Grid>
                ))}
            </Grid>

            <CountryForm
                open={openAddDialog}
                handleClose={() => setOpenAddDialog(false)}
                onSubmit={handleCreate}
            />

            <CountryForm
                open={openEditDialog}
                handleClose={() => setOpenEditDialog(false)}
                country={selectedCountry}
                onSubmit={handleUpdate}
            />

            <Dialog open={openDeleteDialog} onClose={() => setOpenDeleteDialog(false)}>
                <DialogTitle>Confirm Delete</DialogTitle>
                <DialogContent>
                    Are you sure you want to delete the country "{selectedCountry?.name}"?
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => setOpenDeleteDialog(false)}>Cancel</Button>
                    <Button onClick={handleDelete} color="error" variant="contained">
                        Delete
                    </Button>
                </DialogActions>
            </Dialog>
        </Box>
    );
};

export default CountryList;