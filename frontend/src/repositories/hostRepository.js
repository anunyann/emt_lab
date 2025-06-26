import apiClient from '../api/apiConfig';

const HOSTS_ENDPOINT = '/hosts';

const hostRepository = {
    getAll: async () => {
        try {
            const response = await apiClient.get(HOSTS_ENDPOINT);
            return response.data;
        } catch (error) {
            console.error('Error fetching hosts:', error);
            throw error;
        }
    },

    getById: async (id) => {
        try {
            const response = await apiClient.get(`${HOSTS_ENDPOINT}/${id}`);
            return response.data;
        } catch (error) {
            console.error(`Error fetching host with id ${id}:`, error);
            throw error;
        }
    },

    create: async (host) => {
        try {
            const response = await apiClient.post(HOSTS_ENDPOINT, host);
            return response.data;
        } catch (error) {
            console.error('Error creating host:', error);
            throw error;
        }
    },

    update: async (id, host) => {
        try {
            const response = await apiClient.put(`${HOSTS_ENDPOINT}/${id}`, host);
            return response.data;
        } catch (error) {
            console.error(`Error updating host with id ${id}:`, error);
            throw error;
        }
    },

    delete: async (id) => {
        try {
            await apiClient.delete(`${HOSTS_ENDPOINT}/${id}`);
            return true;
        } catch (error) {
            console.error(`Error deleting host with id ${id}:`, error);
            throw error;
        }
    }
};

export default hostRepository;