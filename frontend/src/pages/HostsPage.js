import React from 'react';
import { Box, CircularProgress, Alert } from '@mui/material';
import useHosts from '../hooks/useHosts';
import HostList from '../components/Host/HostList';

const HostsPage = () => {
    const { loading, error } = useHosts();

    if (loading) {
        return (
            <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}>
                <CircularProgress />
            </Box>
        );
    }

    if (error) {
        return <Alert severity="error">Error loading hosts: {error}</Alert>;
    }

    return (
        <Box sx={{ p: 2 }}>
            <HostList />
        </Box>
    );
};

export default HostsPage;