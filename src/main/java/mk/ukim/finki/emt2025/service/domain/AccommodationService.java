package mk.ukim.finki.emt2025.service.domain;

import mk.ukim.finki.emt2025.model.domain.Accommodation;
import mk.ukim.finki.emt2025.model.dto.AccommodationCreateDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> create(AccommodationCreateDto accommodationDto);
    Optional<Accommodation> update(Long id, AccommodationCreateDto accommodationDto);
    Optional<Accommodation> markAsRented(Long id);
    void deleteById(Long id);
    List<Accommodation> findAvailableAccommodations();
}