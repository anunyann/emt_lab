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
import {
    Edit as EditIcon,
    Delete as DeleteIcon,
    Room as RoomIcon,
    Person as PersonIcon,
    Public as PublicIcon,
    KingBed as BedroomIcon,
    Lock as LockIcon,
    CheckCircle as CheckCircleIcon
} from '@mui/icons-material';
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
                        <Paper
                            sx={{
                                p: 0,
                                height: '100%',
                                display: 'flex',
                                flexDirection: 'column',
                                overflow: 'hidden',
                                borderRadius: 2,
                                transition: 'transform 0.3s, box-shadow 0.3s',
                                '&:hover': {
                                    transform: 'translateY(-5px)',
                                    boxShadow: 6
                                }
                            }}
                        >
                            {/* Card Header Image */}
                            <Box
                                sx={{
                                    height: 180,
                                    bgcolor: (theme) => theme.palette.primary.light,
                                    position: 'relative',
                                    overflow: 'hidden'
                                }}
                            >
                                {/* Add an icon or image placeholder here */}
                                <Box
                                    sx={{
                                        position: 'absolute',
                                        top: 0,
                                        right: 0,
                                        p: 1,
                                        display: 'flex',
                                        gap: 0.5
                                    }}
                                >
                                    <IconButton
                                        size="small"
                                        color="primary"
                                        onClick={(e) => {
                                            e.stopPropagation();
                                            handleOpenEdit(accommodation);
                                        }}
                                        sx={{
                                            bgcolor: 'white',
                                            '&:hover': { bgcolor: 'rgba(255,255,255,0.9)' }
                                        }}
                                    >
                                        <EditIcon fontSize="small" />
                                    </IconButton>
                                    <IconButton
                                        size="small"
                                        color="error"
                                        onClick={(e) => {
                                            e.stopPropagation();
                                            handleOpenDelete(accommodation);
                                        }}
                                        sx={{
                                            bgcolor: 'white',
                                            '&:hover': { bgcolor: 'rgba(255,255,255,0.9)' }
                                        }}
                                    >
                                        <DeleteIcon fontSize="small" />
                                    </IconButton>
                                </Box>

                                {/* Category Badge */}
                                <Chip
                                    label={accommodation.category}
                                    color="primary"
                                    size="small"
                                    sx={{
                                        position: 'absolute',
                                        bottom: 10,
                                        left: 10,
                                        fontWeight: 'bold'
                                    }}
                                />
                            </Box>

                            {/* Card Content */}
                            <Box sx={{ p: 2, flexGrow: 1, display: 'flex', flexDirection: 'column' }}>
                                <Typography variant="h6" gutterBottom>
                                    {accommodation.name}
                                </Typography>

                                <Box sx={{ display: 'flex', alignItems: 'center', mb: 1 }}>
                                    <PersonIcon fontSize="small" color="action" sx={{ mr: 1 }} />
                                    <Typography variant="body2">
                                        {accommodation.host?.name} {accommodation.host?.surname}
                                    </Typography>
                                </Box>

                                <Box sx={{ display: 'flex', alignItems: 'center', mb: 1 }}>
                                    <PublicIcon fontSize="small" color="action" sx={{ mr: 1 }} />
                                    <Typography variant="body2">
                                        {accommodation.host?.country?.name}
                                    </Typography>
                                </Box>

                                <Box sx={{ display: 'flex', alignItems: 'center', mb: 2 }}>
                                    <BedroomIcon fontSize="small" color="action" sx={{ mr: 1 }} />
                                    <Typography variant="body2">
                                        {accommodation.numRooms} {accommodation.numRooms === 1 ? 'Room' : 'Rooms'}
                                    </Typography>
                                </Box>

                                <Box sx={{ mt: 'auto' }}>
                                    {accommodation.isRented ? (
                                        <Chip
                                            color="error"
                                            label="Rented"
                                            icon={<LockIcon />}
                                            variant="outlined"
                                            sx={{ width: '100%' }}
                                        />
                                    ) : (
                                        <Button
                                            variant="contained"
                                            fullWidth
                                            startIcon={<CheckCircleIcon />}
                                            onClick={() => handleRent(accommodation.id)}
                                            color="success"
                                        >
                                            Mark as Rented
                                        </Button>
                                    )}
                                </Box>
                            </Box>
                        </Paper>
                    </Grid>
                ))}
            </Grid>

            {/* Add Accommodation Dialog */}
            <AccommodationForm
                open={openAddDialog}
                handleClose={() => setOpenAddDialog(false)}
                onSubmit={handleCreate}
            />

            {/* Edit Accommodation Dialog */}
            <AccommodationForm
                open={openEditDialog}
                handleClose={() => setOpenEditDialog(false)}
                accommodation={selectedAccommodation}
                onSubmit={handleUpdate}
            />

            {/* Delete Confirmation Dialog */}
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