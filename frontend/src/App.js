import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Layout from './components/Layout/Layout';
import HomePage from './pages/HomePage';
import AccommodationsPage from './pages/AccommodationsPage';
import HostsPage from './pages/HostsPage';
import CountriesPage from './pages/CountriesPage';

function App() {
  return (
      <Router>
        <Layout>
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/accommodations" element={<AccommodationsPage />} />
            <Route path="/hosts" element={<HostsPage />} />
            <Route path="/countries" element={<CountriesPage />} />
          </Routes>
        </Layout>
      </Router>
  );
}

export default App;