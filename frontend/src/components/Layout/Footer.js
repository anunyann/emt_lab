import React from 'react';
import { Box, Typography, Container } from '@mui/material';

const Footer = () => {
    return (
        <Box component="footer" sx={{ py: 3, mt: 'auto', backgroundColor: 'primary.light' }}>
            <Container maxWidth="md">
                <Typography variant="body2" color="text.secondary" align="center">
                    © {new Date().getFullYear()} Accommodation Booking App
                </Typography>
            </Container>
        </Box>
    );
};

export default Footer;