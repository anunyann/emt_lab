import apiClient from '../api/apiConfig';

const ACCOMMODATIONS_ENDPOINT = '/accommodations';

const accommodationRepository = {
    getAll: async () => {
        try {
            const response = await apiClient.get(ACCOMMODATIONS_ENDPOINT);
            return response.data;
        } catch (error) {
            console.error('Error fetching accommodations:', error);
            throw error;
        }
    },

    getById: async (id) => {
        try {
            const response = await apiClient.get(`${ACCOMMODATIONS_ENDPOINT}/${id}`);
            return response.data;
        } catch (error) {
            console.error(`Error fetching accommodation with id ${id}:`, error);
            throw error;
        }
    },

    getAvailable: async () => {
        try {
            const response = await apiClient.get(`${ACCOMMODATIONS_ENDPOINT}/available`);
            return response.data;
        } catch (error) {
            console.error('Error fetching available accommodations:', error);
            throw error;
        }
    },

    create: async (accommodation) => {
        try {
            const response = await apiClient.post(ACCOMMODATIONS_ENDPOINT, accommodation);
            return response.data;
        } catch (error) {
            console.error('Error creating accommodation:', error);
            throw error;
        }
    },

    update: async (id, accommodation) => {
        try {
            const response = await apiClient.put(`${ACCOMMODATIONS_ENDPOINT}/${id}`, accommodation);
            return response.data;
        } catch (error) {
            console.error(`Error updating accommodation with id ${id}:`, error);
            throw error;
        }
    },

    markAsRented: async (id) => {
        try {
            const response = await apiClient.put(`${ACCOMMODATIONS_ENDPOINT}/${id}/rent`);
            return response.data;
        } catch (error) {
            console.error(`Error marking accommodation ${id} as rented:`, error);
            throw error;
        }
    },

    delete: async (id) => {
        try {
            await apiClient.delete(`${ACCOMMODATIONS_ENDPOINT}/${id}`);
            return true;
        } catch (error) {
            console.error(`Error deleting accommodation with id ${id}:`, error);
            throw error;
        }
    }
};

export default accommodationRepository;