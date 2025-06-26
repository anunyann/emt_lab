package mk.ukim.finki.emt2025.service.application.impl;

import mk.ukim.finki.emt2025.model.domain.Accommodation;
import mk.ukim.finki.emt2025.model.dto.AccommodationCreateDto;
import mk.ukim.finki.emt2025.model.dto.AccommodationDto;
import mk.ukim.finki.emt2025.model.dto.HostDto;
import mk.ukim.finki.emt2025.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt2025.service.application.HostApplicationService;
import mk.ukim.finki.emt2025.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {

    private final AccommodationService accommodationService;
    private final HostApplicationService hostApplicationService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService,
                                               HostApplicationService hostApplicationService) {
        this.accommodationService = accommodationService;
        this.hostApplicationService = hostApplicationService;
    }

    @Override
    public List<AccommodationDto> findAll() {
        return this.accommodationService.findAll().stream()
                .map(accommodation -> {
                    HostDto hostDto = this.hostApplicationService.findById(accommodation.getHost().getId()).orElse(null);
                    return AccommodationDto.from(accommodation, hostDto);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AccommodationDto> findById(Long id) {
        return this.accommodationService.findById(id)
                .map(accommodation -> {
                    HostDto hostDto = this.hostApplicationService.findById(accommodation.getHost().getId()).orElse(null);
                    return AccommodationDto.from(accommodation, hostDto);
                });
    }

    @Override
    public Optional<AccommodationDto> create(AccommodationCreateDto accommodationDto) {
        return this.accommodationService.create(accommodationDto)
                .map(accommodation -> {
                    HostDto hostDto = this.hostApplicationService.findById(accommodation.getHost().getId()).orElse(null);
                    return AccommodationDto.from(accommodation, hostDto);
                });
    }

    @Override
    public Optional<AccommodationDto> update(Long id, AccommodationCreateDto accommodationDto) {
        return this.accommodationService.update(id, accommodationDto)
                .map(accommodation -> {
                    HostDto hostDto = this.hostApplicationService.findById(accommodation.getHost().getId()).orElse(null);
                    return AccommodationDto.from(accommodation, hostDto);
                });
    }

    @Override
    public Optional<AccommodationDto> markAsRented(Long id) {
        return this.accommodationService.markAsRented(id)
                .map(accommodation -> {
                    HostDto hostDto = this.hostApplicationService.findById(accommodation.getHost().getId()).orElse(null);
                    return AccommodationDto.from(accommodation, hostDto);
                });
    }

    @Override
    public void deleteById(Long id) {
        this.accommodationService.deleteById(id);
    }

    @Override
    public List<AccommodationDto> findAvailableAccommodations() {
        return this.accommodationService.findAvailableAccommodations().stream()
                .map(accommodation -> {
                    HostDto hostDto = this.hostApplicationService.findById(accommodation.getHost().getId()).orElse(null);
                    return AccommodationDto.from(accommodation, hostDto);
                })
                .collect(Collectors.toList());
    }
}