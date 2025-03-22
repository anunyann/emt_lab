package mk.ukim.finki.emt2025.service;

import mk.ukim.finki.emt2025.model.Country;
import mk.ukim.finki.emt2025.model.dto.CountryCreateDto;
import mk.ukim.finki.emt2025.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Optional<Country> create(CountryCreateDto countryDto);
    Optional<Country> update(Long id, CountryCreateDto countryDto);
    void deleteById(Long id);
    CountryDto toDto(Country country);
}
