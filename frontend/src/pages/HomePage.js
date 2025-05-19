import React from 'react';
import { Typography, Paper, Box } from '@mui/material';

const HomePage = () => {
    return (
        <Paper elevation={3} sx={{ p: 4 }}>
            <Typography variant="h4" gutterBottom>
                Welcome to Accommodation Booking
            </Typography>
            <Typography variant="body1">
                Browse available accommodations, hosts, and countries using the navigation above.
            </Typography>
            <Box sx={{ mt: 4 }}>
                <Typography variant="h6" gutterBottom>
                    Features:
                </Typography>
                <ul>
                    <li>Browse and manage accommodations</li>
                    <li>View host information</li>
                    <li>Explore accommodation by country</li>
                </ul>
            </Box>
        </Paper>
    );
};

export default HomePage;