import { useState, useEffect, useCallback } from 'react';
import countryRepository from '../repositories/countryRepository';

const useCountries = () => {
    const [countries, setCountries] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const fetchCountries = useCallback(async () => {
        setLoading(true);
        setError(null);
        try {
            const data = await countryRepository.getAll();
            setCountries(data);
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    }, []);

    useEffect(() => {
        fetchCountries();
    }, [fetchCountries]);

    const createCountry = async (country) => {
        setLoading(true);
        setError(null);
        try {
            await countryRepository.create(country);
            await fetchCountries(); // Refresh the list
            return true;
        } catch (err) {
            setError(err.message);
            return false;
        } finally {
            setLoading(false);
        }
    };

    const updateCountry = async (id, country) => {
        setLoading(true);
        setError(null);
        try {
            await countryRepository.update(id, country);
            await fetchCountries(); // Refresh the list
            return true;
        } catch (err) {
            setError(err.message);
            return false;
        } finally {
            setLoading(false);
        }
    };

    const deleteCountry = async (id) => {
        setLoading(true);
        setError(null);
        try {
            await countryRepository.delete(id);
            await fetchCountries(); // Refresh the list
            return true;
        } catch (err) {
            setError(err.message);
            return false;
        } finally {
            setLoading(false);
        }
    };

    return {
        countries,
        loading,
        error,
        fetchCountries,
        createCountry,
        updateCountry,
        deleteCountry
    };
};

export default useCountries;