package mk.ukim.finki.emt2025.service.application;

import mk.ukim.finki.emt2025.model.dto.AccommodationCreateDto;
import mk.ukim.finki.emt2025.model.dto.AccommodationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<AccommodationDto> findAll();
    Optional<AccommodationDto> findById(Long id);
    Optional<AccommodationDto> create(AccommodationCreateDto accommodationDto);
    Optional<AccommodationDto> update(Long id, AccommodationCreateDto accommodationDto);
    Optional<AccommodationDto> markAsRented(Long id);
    void deleteById(Long id);
    List<AccommodationDto> findAvailableAccommodations();
}