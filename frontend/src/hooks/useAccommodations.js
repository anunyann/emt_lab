import { useState, useEffect, useCallback } from 'react';
import accommodationRepository from '../repositories/accommodationRepository';

const useAccommodations = () => {
    const [accommodations, setAccommodations] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchAccommodations = useCallback(async () => {
        setLoading(true);
        setError(null);
        try {
            const data = await accommodationRepository.getAll();
            setAccommodations(data);
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    }, []);

    useEffect(() => {
        fetchAccommodations();
    }, [fetchAccommodations]);

    const createAccommodation = async (accommodation) => {
        setLoading(true);
        setError(null);
        try {
            await accommodationRepository.create(accommodation);
            await fetchAccommodations(); // Refresh the list
            return true;
        } catch (err) {
            setError(err.message);
            return false;
        } finally {
            setLoading(false);
        }
    };

    const updateAccommodation = async (id, accommodation) => {
        setLoading(true);
        setError(null);
        try {
            await accommodationRepository.update(id, accommodation);
            await fetchAccommodations(); // Refresh the list
            return true;
        } catch (err) {
            setError(err.message);
            return false;
        } finally {
            setLoading(false);
        }
    };

    const deleteAccommodation = async (id) => {
        setLoading(true);
        setError(null);
        try {
            await accommodationRepository.delete(id);
            await fetchAccommodations(); // Refresh the list
            return true;
        } catch (err) {
            setError(err.message);
            return false;
        } finally {
            setLoading(false);
        }
    };

    const markAsRented = async (id) => {
        setLoading(true);
        setError(null);
        try {
            await accommodationRepository.markAsRented(id);
            await fetchAccommodations(); // Refresh the list
            return true;
        } catch (err) {
            setError(err.message);
            return false;
        } finally {
            setLoading(false);
        }
    };

    return {
        accommodations,
        loading,
        error,
        fetchAccommodations,
        createAccommodation,
        updateAccommodation,
        deleteAccommodation,
        markAsRented
    };
};

export default useAccommodations;