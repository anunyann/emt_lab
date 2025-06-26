// src/components/Layout/Header.js
import React, { useState } from 'react';
import {
    AppBar,
    Toolbar,
    Typography,
    Button,
    Box,
    IconButton,
    Drawer,
    List,
    ListItem,
    ListItemText,
    ListItemIcon,
    useMediaQuery,
    useTheme
} from '@mui/material';
import { Link } from 'react-router-dom';
import MenuIcon from '@mui/icons-material/Menu';
import HomeIcon from '@mui/icons-material/Home';
import BedIcon from '@mui/icons-material/Bed';
import PersonIcon from '@mui/icons-material/Person';
import PublicIcon from '@mui/icons-material/Public';

const Header = () => {
    const theme = useTheme();
    const isMobile = useMediaQuery(theme.breakpoints.down('md'));
    const [drawerOpen, setDrawerOpen] = useState(false);

    const toggleDrawer = (open) => (event) => {
        if (
            event.type === 'keydown' &&
            (event.key === 'Tab' || event.key === 'Shift')
        ) {
            return;
        }
        setDrawerOpen(open);
    };

    const navLinks = [
        { title: 'Home', path: '/', icon: <HomeIcon /> },
        { title: 'Accommodations', path: '/accommodations', icon: <BedIcon /> },
        { title: 'Hosts', path: '/hosts', icon: <PersonIcon /> },
        { title: 'Countries', path: '/countries', icon: <PublicIcon /> },
    ];

    const drawer = (
        <Box
            sx={{ width: 250 }}
            role="presentation"
            onClick={toggleDrawer(false)}
            onKeyDown={toggleDrawer(false)}
        >
            <List>
                {navLinks.map((link) => (
                    <ListItem button key={link.title} component={Link} to={link.path}>
                        <ListItemIcon>{link.icon}</ListItemIcon>
                        <ListItemText primary={link.title} />
                    </ListItem>
                ))}
            </List>
        </Box>
    );

    return (
        <AppBar position="static" elevation={2} sx={{ backgroundColor: 'white', color: 'primary.main' }}>
            <Toolbar>
                <Typography
                    variant="h6"
                    component={Link}
                    to="/"
                    sx={{
                        flexGrow: 1,
                        fontWeight: 'bold',
                        textDecoration: 'none',
                        color: 'inherit',
                        display: 'flex',
                        alignItems: 'center'
                    }}
                >
                    <BedIcon sx={{ mr: 1 }} /> Accommodation Booking
                </Typography>

                {isMobile ? (
                    <>
                        <IconButton
                            edge="end"
                            color="inherit"
                            aria-label="menu"
                            onClick={toggleDrawer(true)}
                        >
                            <MenuIcon />
                        </IconButton>
                        <Drawer
                            anchor="right"
                            open={drawerOpen}
                            onClose={toggleDrawer(false)}
                        >
                            {drawer}
                        </Drawer>
                    </>
                ) : (
                    <Box sx={{ display: 'flex', gap: 2 }}>
                        {navLinks.map((link) => (
                            <Button
                                key={link.title}
                                color="inherit"
                                component={Link}
                                to={link.path}
                                startIcon={link.icon}
                                sx={{
                                    fontWeight: 'medium',
                                    '&:hover': {
                                        backgroundColor: 'rgba(25, 118, 210, 0.08)',
                                        borderRadius: 1
                                    }
                                }}
                            >
                                {link.title}
                            </Button>
                        ))}
                    </Box>
                )}
            </Toolbar>
        </AppBar>
    );
};

export default Header;