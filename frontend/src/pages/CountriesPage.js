import React from 'react';
import { Box, CircularProgress, Alert } from '@mui/material';
import useCountries from '../hooks/useCountries';
import CountryList from '../components/Country/CountryList';

const CountriesPage = () => {
    const { loading, error } = useCountries();

    if (loading) {
        return (
            <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}>
                <CircularProgress />
            </Box>
        );
    }

    if (error) {
        return <Alert severity="error">Error loading countries: {error}</Alert>;
    }

    return (
        <Box sx={{ p: 2 }}>
            <CountryList />
        </Box>
    );
};

export default CountriesPage;