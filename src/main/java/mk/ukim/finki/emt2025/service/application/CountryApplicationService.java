package mk.ukim.finki.emt2025.service.application;

import mk.ukim.finki.emt2025.model.dto.CountryCreateDto;
import mk.ukim.finki.emt2025.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<CountryDto> findAll();
    Optional<CountryDto> findById(Long id);
    Optional<CountryDto> create(CountryCreateDto countryDto);
    Optional<CountryDto> update(Long id, CountryCreateDto countryDto);
    void deleteById(Long id);
}