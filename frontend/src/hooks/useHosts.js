import { useState, useEffect, useCallback } from 'react';
import hostRepository from '../repositories/hostRepository';

const useHosts = () => {
    const [hosts, setHosts] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchHosts = useCallback(async () => {
        setLoading(true);
        setError(null);
        try {
            const data = await hostRepository.getAll();
            setHosts(data);
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    }, []);

    useEffect(() => {
        fetchHosts();
    }, [fetchHosts]);

    const createHost = async (host) => {
        setLoading(true);
        setError(null);
        try {
            await hostRepository.create(host);
            await fetchHosts(); // Refresh the list
            return true;
        } catch (err) {
            setError(err.message);
            return false;
        } finally {
            setLoading(false);
        }
    };

    const updateHost = async (id, host) => {
        setLoading(true);
        setError(null);
        try {
            await hostRepository.update(id, host);
            await fetchHosts(); // Refresh the list
            return true;
        } catch (err) {
            setError(err.message);
            return false;
        } finally {
            setLoading(false);
        }
    };

    const deleteHost = async (id) => {
        setLoading(true);
        setError(null);
        try {
            await hostRepository.delete(id);
            await fetchHosts(); // Refresh the list
            return true;
        } catch (err) {
            setError(err.message);
            return false;
        } finally {
            setLoading(false);
        }
    };

    return {
        hosts,
        loading,
        error,
        fetchHosts,
        createHost,
        updateHost,
        deleteHost
    };
};

export default useHosts;