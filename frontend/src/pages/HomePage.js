import React from 'react';
import { Typography, Button, Box, Container, Grid, Paper } from '@mui/material';
import { Link } from 'react-router-dom';
import BedIcon from '@mui/icons-material/Bed';
import PersonIcon from '@mui/icons-material/Person';
import PublicIcon from '@mui/icons-material/Public';

const HomePage = () => {
    return (
        <Box>
            {/* Hero Section */}
            <Box
                sx={{
                    bgcolor: 'primary.main',
                    color: 'white',
                    py: 8,
                    borderRadius: 2,
                    mb: 6,
                    backgroundImage: 'linear-gradient(135deg, #1976d2 0%, #64b5f6 100%)',
                    boxShadow: 3,
                }}
            >
                <Container maxWidth="lg">
                    <Grid container spacing={4} alignItems="center">
                        <Grid item xs={12} md={6}>
                            <Typography variant="h2" component="h1" gutterBottom fontWeight="bold">
                                Find Your Perfect Stay
                            </Typography>
                            <Typography variant="h5" paragraph>
                                Browse through our curated collection of accommodations worldwide.
                            </Typography>
                            <Button
                                variant="contained"
                                size="large"
                                component={Link}
                                to="/accommodations"
                                sx={{
                                    mt: 2,
                                    bgcolor: 'white',
                                    color: 'primary.main',
                                    '&:hover': {
                                        bgcolor: 'grey.100',
                                    }
                                }}
                            >
                                Browse Accommodations
                            </Button>
                        </Grid>
                        <Grid item xs={12} md={6}>
                            <Box
                                component="img"
                                src="https://images.unsplash.com/photo-1566073771259-6a8506099945?auto=format&fit=crop&w=800&q=80"
                                alt="Luxury accommodation"
                                sx={{
                                    width: '100%',
                                    borderRadius: 4,
                                    boxShadow: 10,
                                    transform: 'rotate(2deg)',
                                }}
                            />
                        </Grid>
                    </Grid>
                </Container>
            </Box>

            {/* Feature Cards */}
            <Container maxWidth="lg">
                <Grid container spacing={4} sx={{ mb: 6 }}>
                    <Grid item xs={12} md={4}>
                        <Paper elevation={3} sx={{ p: 3, height: '100%', borderRadius: 2 }}>
                            <Box sx={{ display: 'flex', alignItems: 'center', mb: 2 }}>
                                <BedIcon color="primary" fontSize="large" sx={{ mr: 1 }} />
                                <Typography variant="h5" component="h2">
                                    Accommodations
                                </Typography>
                            </Box>
                            <Typography variant="body1" paragraph>
                                Browse our selection of rooms, apartments, houses and more.
                            </Typography>
                            <Button
                                component={Link}
                                to="/accommodations"
                                variant="outlined"
                                color="primary"
                            >
                                View All
                            </Button>
                        </Paper>
                    </Grid>
                    <Grid item xs={12} md={4}>
                        <Paper elevation={3} sx={{ p: 3, height: '100%', borderRadius: 2 }}>
                            <Box sx={{ display: 'flex', alignItems: 'center', mb: 2 }}>
                                <PersonIcon color="primary" fontSize="large" sx={{ mr: 1 }} />
                                <Typography variant="h5" component="h2">
                                    Hosts
                                </Typography>
                            </Box>
                            <Typography variant="body1" paragraph>
                                Meet our friendly and professional hosts from around the world.
                            </Typography>
                            <Button
                                component={Link}
                                to="/hosts"
                                variant="outlined"
                                color="primary"
                            >
                                View All
                            </Button>
                        </Paper>
                    </Grid>
                    <Grid item xs={12} md={4}>
                        <Paper elevation={3} sx={{ p: 3, height: '100%', borderRadius: 2 }}>
                            <Box sx={{ display: 'flex', alignItems: 'center', mb: 2 }}>
                                <PublicIcon color="primary" fontSize="large" sx={{ mr: 1 }} />
                                <Typography variant="h5" component="h2">
                                    Countries
                                </Typography>
                            </Box>
                            <Typography variant="body1" paragraph>
                                Explore accommodations by destination across continents.
                            </Typography>
                            <Button
                                component={Link}
                                to="/countries"
                                variant="outlined"
                                color="primary"
                            >
                                View All
                            </Button>
                        </Paper>
                    </Grid>
                </Grid>
            </Container>
        </Box>
    );
};

export default HomePage;