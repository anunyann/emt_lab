package mk.ukim.finki.emt2025.service.impl;

import mk.ukim.finki.emt2025.model.Country;
import mk.ukim.finki.emt2025.model.dto.CountryCreateDto;
import mk.ukim.finki.emt2025.model.dto.CountryDto;
import mk.ukim.finki.emt2025.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt2025.repository.CountryRepository;
import mk.ukim.finki.emt2025.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Optional<Country> create(CountryCreateDto countryDto) {
        Country country = new Country(countryDto.getName(), countryDto.getContinent());
        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> update(Long id, CountryCreateDto countryDto) {
        Country country = this.countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));

        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());

        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }

    @Override
    public CountryDto toDto(Country country) {
        return new CountryDto(
                country.getId(),
                country.getName(),
                country.getContinent()
        );
    }
}
