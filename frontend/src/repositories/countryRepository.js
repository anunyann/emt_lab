import apiClient from '../api/apiConfig';

const COUNTRIES_ENDPOINT = '/countries';

const countryRepository = {
    getAll: async () => {
        try {
            const response = await apiClient.get(COUNTRIES_ENDPOINT);
            return response.data;
        } catch (error) {
            console.error('Error fetching countries:', error);
            throw error;
        }
    },

    getById: async (id) => {
        try {
            const response = await apiClient.get(`${COUNTRIES_ENDPOINT}/${id}`);
            return response.data;
        } catch (error) {
            console.error(`Error fetching country with id ${id}:`, error);
            throw error;
        }
    },

    create: async (country) => {
        try {
            const response = await apiClient.post(COUNTRIES_ENDPOINT, country);
            return response.data;
        } catch (error) {
            console.error('Error creating country:', error);
            throw error;
        }
    },

    update: async (id, country) => {
        try {
            const response = await apiClient.put(`${COUNTRIES_ENDPOINT}/${id}`, country);
            return response.data;
        } catch (error) {
            console.error(`Error updating country with id ${id}:`, error);
            throw error;
        }
    },

    delete: async (id) => {
        try {
            await apiClient.delete(`${COUNTRIES_ENDPOINT}/${id}`);
            return true;
        } catch (error) {
            console.error(`Error deleting country with id ${id}:`, error);
            throw error;
        }
    }
};

export default countryRepository;