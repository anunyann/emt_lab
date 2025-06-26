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
    Avatar
} from '@mui/material';
import { Edit as EditIcon, Delete as DeleteIcon } from '@mui/icons-material';
import { deepOrange } from '@mui/material/colors';
import HostForm from './HostForm';
import useHosts from '../../hooks/useHosts';

const HostList = () => {
    const { hosts, createHost, updateHost, deleteHost } = useHosts();

    const [openAddDialog, setOpenAddDialog] = useState(false);
    const [openEditDialog, setOpenEditDialog] = useState(false);
    const [openDeleteDialog, setOpenDeleteDialog] = useState(false);
    const [selectedHost, setSelectedHost] = useState(null);

    const handleOpenEdit = (host) => {
        setSelectedHost(host);
        setOpenEditDialog(true);
    };

    const handleOpenDelete = (host) => {
        setSelectedHost(host);
        setOpenDeleteDialog(true);
    };

    const handleCreate = (formData) => {
        createHost(formData);
    };

    const handleUpdate = (formData) => {
        if (selectedHost) {
            updateHost(selectedHost.id, formData);
        }
    };

    const handleDelete = () => {
        if (selectedHost) {
            deleteHost(selectedHost.id);
            setOpenDeleteDialog(false);
        }
    };

    return (
        <Box>
            <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 3 }}>
                <Typography variant="h5">Hosts</Typography>
                <Button
                    variant="contained"
                    color="primary"
                    onClick={() => setOpenAddDialog(true)}
                >
                    Add Host
                </Button>
            </Box>

            <Grid container spacing={2}>
                {hosts.map(host => (
                    <Grid item xs={12} sm={6} md={4} key={host.id}>
                        <Paper sx={{ p: 2, height: '100%', display: 'flex', flexDirection: 'column' }}>
                            <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 2 }}>
                                <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
                                    <Avatar sx={{ bgcolor: deepOrange[500] }}>
                                        {host.name.charAt(0)}{host.surname.charAt(0)}
                                    </Avatar>
                                    <Typography variant="h6">
                                        {host.name} {host.surname}
                                    </Typography>
                                </Box>
                                <Box>
                                    <IconButton
                                        size="small"
                                        color="primary"
                                        onClick={() => handleOpenEdit(host)}
                                    >
                                        <EditIcon />
                                    </IconButton>
                                    <IconButton
                                        size="small"
                                        color="error"
                                        onClick={() => handleOpenDelete(host)}
                                    >
                                        <DeleteIcon />
                                    </IconButton>
                                </Box>
                            </Box>

                            <Typography variant="body2">
                                <strong>Country:</strong> {host.country?.name}
                            </Typography>
                            <Typography variant="body2">
                                <strong>Continent:</strong> {host.country?.continent}
                            </Typography>
                        </Paper>
                    </Grid>
                ))}
            </Grid>

            <HostForm
                open={openAddDialog}
                handleClose={() => setOpenAddDialog(false)}
                onSubmit={handleCreate}
            />

            <HostForm
                open={openEditDialog}
                handleClose={() => setOpenEditDialog(false)}
                host={selectedHost}
                onSubmit={handleUpdate}
            />

            <Dialog open={openDeleteDialog} onClose={() => setOpenDeleteDialog(false)}>
                <DialogTitle>Confirm Delete</DialogTitle>
                <DialogContent>
                    Are you sure you want to delete the host "{selectedHost?.name} {selectedHost?.surname}"?
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

export default HostList;