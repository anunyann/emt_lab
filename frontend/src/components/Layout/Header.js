import React from 'react';
import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material';
import { Link } from 'react-router-dom';

const Header = () => {
    return (
        <AppBar position="static">
            <Toolbar>
                <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                    Accommodation Booking
                </Typography>
                <Box sx={{ display: 'flex', gap: 2 }}>
                    <Button color="inherit" component={Link} to="/">Home</Button>
                    <Button color="inherit" component={Link} to="/accommodations">Accommodations</Button>
                    <Button color="inherit" component={Link} to="/hosts">Hosts</Button>
                    <Button color="inherit" component={Link} to="/countries">Countries</Button>
                </Box>
            </Toolbar>
        </AppBar>
    );
};

export default Header;