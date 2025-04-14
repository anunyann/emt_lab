package mk.ukim.finki.emt2025.service.application.impl;

import mk.ukim.finki.emt2025.model.dto.CountryCreateDto;
import mk.ukim.finki.emt2025.model.dto.CountryDto;
import mk.ukim.finki.emt2025.service.application.CountryApplicationService;
import mk.ukim.finki.emt2025.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<CountryDto> findAll() {
        return this.countryService.findAll().stream()
                .map(CountryDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CountryDto> findById(Long id) {
        return this.countryService.findById(id)
                .map(CountryDto::from);
    }

    @Override
    public Optional<CountryDto> create(CountryCreateDto countryDto) {
        return this.countryService.create(countryDto)
                .map(CountryDto::from);
    }

    @Override
    public Optional<CountryDto> update(Long id, CountryCreateDto countryDto) {
        return this.countryService.update(id, countryDto)
                .map(CountryDto::from);
    }

    @Override
    public void deleteById(Long id) {
        this.countryService.deleteById(id);
    }
}