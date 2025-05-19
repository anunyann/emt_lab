import React, { useState } from 'react';
import {
    Typography,
    Button,
    Paper,
    Box,
    IconButton,
    Chip,
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    Grid
} from '@mui/material';
import { Edit as EditIcon, Delete as DeleteIcon, Room as RoomIcon } from '@mui/icons-material';
import AccommodationForm from './AccommodationForm';
import useAccommodations from '../../hooks/useAccommodations';

const AccommodationList = () => {
    const {
        accommodations,
        createAccommodation,
        updateAccommodation,
        deleteAccommodation,
        markAsRented
    } = useAccommodations();

    const [openAddDialog, setOpenAddDialog] = useState(false);
    const [openEditDialog, setOpenEditDialog] = useState(false);
    const [openDeleteDialog, setOpenDeleteDialog] = useState(false);
    const [selectedAccommodation, setSelectedAccommodation] = useState(null);

    const handleOpenEdit = (accommodation) => {
        setSelectedAccommodation(accommodation);
        setOpenEditDialog(true);
    };

    const handleOpenDelete = (accommodation) => {
        setSelectedAccommodation(accommodation);
        setOpenDeleteDialog(true);
    };

    const handleCreate = (formData) => {
        createAccommodation(formData);
    };

    const handleUpdate = (formData) => {
        if (selectedAccommodation) {
            updateAccommodation(selectedAccommodation.id, formData);
        }
    };

    const handleDelete = () => {
        if (selectedAccommodation) {
            deleteAccommodation(selectedAccommodation.id);
            setOpenDeleteDialog(false);
        }
    };

    const handleRent = (id) => {
        markAsRented(id);
    };

    return (
        <Box>
            <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 3 }}>
                <Typography variant="h5">Accommodations</Typography>
                <Button
                    variant="contained"
                    color="primary"
                    onClick={() => setOpenAddDialog(true)}
                >
                    Add Accommodation
                </Button>
            </Box>

            <Grid container spacing={2}>
                {accommodations.map(accommodation => (
                    <Grid item xs={12} md={6} lg={4} key={accommodation.id}>
                        <Paper sx={{ p: 2, height: '100%', display: 'flex', flexDirection: 'column' }}>
                            <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 1 }}>
                                <Typography variant="h6" gutterBottom>
                                    {accommodation.name}
                                </Typography>
                                <Box>
                                    <IconButton
                                        size="small"
                                        color="primary"
                                        onClick={() => handleOpenEdit(accommodation)}
                                    >
                                        <EditIcon />
                                    </IconButton>
                                    <IconButton
                                        size="small"
                                        color="error"
                                        onClick={() => handleOpenDelete(accommodation)}
                                    >
                                        <DeleteIcon />
                                    </IconButton>
                                </Box>
                            </Box>

                            <Typography variant="body2">
                                <strong>Category:</strong> {accommodation.category}
                            </Typography>
                            <Typography variant="body2">
                                <strong>Host:</strong> {accommodation.host?.name} {accommodation.host?.surname}
                            </Typography>
                            <Typography variant="body2">
                                <strong>Rooms:</strong> {accommodation.numRooms}
                            </Typography>

                            <Box sx={{ mt: 2, display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                                {accommodation.isRented ? (
                                    <Chip color="error" label="Rented" />
                                ) : (
                                    <Button
                                        variant="outlined"
                                        size="small"
                                        startIcon={<RoomIcon />}
                                        onClick={() => handleRent(accommodation.id)}
                                    >
                                        Mark as Rented
                                    </Button>
                                )}
                            </Box>
                        </Paper>
                    </Grid>
                ))}
            </Grid>

            <AccommodationForm
                open={openAddDialog}
                handleClose={() => setOpenAddDialog(false)}
                onSubmit={handleCreate}
            />

            <AccommodationForm
                open={openEditDialog}
                handleClose={() => setOpenEditDialog(false)}
                accommodation={selectedAccommodation}
                onSubmit={handleUpdate}
            />

            <Dialog open={openDeleteDialog} onClose={() => setOpenDeleteDialog(false)}>
                <DialogTitle>Confirm Delete</DialogTitle>
                <DialogContent>
                    Are you sure you want to delete the accommodation "{selectedAccommodation?.name}"?
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

export default AccommodationList;