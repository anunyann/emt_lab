package mk.ukim.finki.emt2025.service.application.impl;

import mk.ukim.finki.emt2025.model.dto.CountryDto;
import mk.ukim.finki.emt2025.model.dto.HostCreateDto;
import mk.ukim.finki.emt2025.model.dto.HostDto;
import mk.ukim.finki.emt2025.service.application.CountryApplicationService;
import mk.ukim.finki.emt2025.service.application.HostApplicationService;
import mk.ukim.finki.emt2025.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {

    private final HostService hostService;
    private final CountryApplicationService countryApplicationService;

    public HostApplicationServiceImpl(HostService hostService, CountryApplicationService countryApplicationService) {
        this.hostService = hostService;
        this.countryApplicationService = countryApplicationService;
    }

    @Override
    public List<HostDto> findAll() {
        return this.hostService.findAll().stream()
                .map(host -> {
                    CountryDto countryDto = this.countryApplicationService.findById(host.getCountry().getId()).orElse(null);
                    return HostDto.from(host, countryDto);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<HostDto> findById(Long id) {
        return this.hostService.findById(id)
                .map(host -> {
                    CountryDto countryDto = this.countryApplicationService.findById(host.getCountry().getId()).orElse(null);
                    return HostDto.from(host, countryDto);
                });
    }

    @Override
    public Optional<HostDto> create(HostCreateDto hostDto) {
        return this.hostService.create(hostDto)
                .map(host -> {
                    CountryDto countryDto = this.countryApplicationService.findById(host.getCountry().getId()).orElse(null);
                    return HostDto.from(host, countryDto);
                });
    }

    @Override
    public Optional<HostDto> update(Long id, HostCreateDto hostDto) {
        return this.hostService.update(id, hostDto)
                .map(host -> {
                    CountryDto countryDto = this.countryApplicationService.findById(host.getCountry().getId()).orElse(null);
                    return HostDto.from(host, countryDto);
                });
    }

    @Override
    public void deleteById(Long id) {
        this.hostService.deleteById(id);
    }
}