import React from 'react';
import { Box, CircularProgress, Alert } from '@mui/material';
import useAccommodations from '../hooks/useAccommodations';
import AccommodationList from '../components/Accommodation/AccommodationList';

const AccommodationsPage = () => {
    const { loading, error } = useAccommodations();

    if (loading) {
        return (
            <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}>
                <CircularProgress />
            </Box>
        );
    }

    if (error) {
        return <Alert severity="error">Error loading accommodations: {error}</Alert>;
    }

    return (
        <Box sx={{ p: 2 }}>
            <AccommodationList />
        </Box>
    );
};

export default AccommodationsPage;