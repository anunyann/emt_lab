package mk.ukim.finki.emt2025.service.impl;

import mk.ukim.finki.emt2025.model.Country;
import mk.ukim.finki.emt2025.model.Host;
import mk.ukim.finki.emt2025.model.dto.HostCreateDto;
import mk.ukim.finki.emt2025.model.dto.HostDto;
import mk.ukim.finki.emt2025.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt2025.model.exceptions.HostNotFoundException;
import mk.ukim.finki.emt2025.repository.CountryRepository;
import mk.ukim.finki.emt2025.repository.HostRepository;
import mk.ukim.finki.emt2025.service.CountryService;
import mk.ukim.finki.emt2025.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;
    private final CountryService countryService;

    public HostServiceImpl(HostRepository hostRepository,
                           CountryRepository countryRepository,
                           CountryService countryService) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Host> findAll() {
        return this.hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return this.hostRepository.findById(id);
    }

    @Override
    public Optional<Host> create(HostCreateDto hostDto) {
        Country country = this.countryRepository.findById(hostDto.getCountryId())
                .orElseThrow(() -> new CountryNotFoundException(hostDto.getCountryId()));

        Host host = new Host(hostDto.getName(), hostDto.getSurname(), country);
        return Optional.of(this.hostRepository.save(host));
    }

    @Override
    public Optional<Host> update(Long id, HostCreateDto hostDto) {
        Host host = this.hostRepository.findById(id)
                .orElseThrow(() -> new HostNotFoundException(id));

        Country country = this.countryRepository.findById(hostDto.getCountryId())
                .orElseThrow(() -> new CountryNotFoundException(hostDto.getCountryId()));

        host.setName(hostDto.getName());
        host.setSurname(hostDto.getSurname());
        host.setCountry(country);

        return Optional.of(this.hostRepository.save(host));
    }

    @Override
    public void deleteById(Long id) {
        this.hostRepository.deleteById(id);
    }

    @Override
    public HostDto toDto(Host host) {
        return new HostDto(
                host.getId(),
                host.getName(),
                host.getSurname(),
                this.countryService.toDto(host.getCountry())
        );
    }
}
