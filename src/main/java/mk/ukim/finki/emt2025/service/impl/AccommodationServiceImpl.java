package mk.ukim.finki.emt2025.service.impl;

import mk.ukim.finki.emt2025.model.Accommodation;
import mk.ukim.finki.emt2025.model.Host;
import mk.ukim.finki.emt2025.model.dto.AccommodationCreateDto;
import mk.ukim.finki.emt2025.model.dto.AccommodationDto;
import mk.ukim.finki.emt2025.model.exceptions.AccommodationNotFoundException;
import mk.ukim.finki.emt2025.model.exceptions.HostNotFoundException;
import mk.ukim.finki.emt2025.repository.AccommodationRepository;
import mk.ukim.finki.emt2025.repository.HostRepository;
import mk.ukim.finki.emt2025.service.AccommodationService;
import mk.ukim.finki.emt2025.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final HostService hostService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository,
                                    HostRepository hostRepository,
                                    HostService hostService) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.hostService = hostService;
    }

    @Override
    public List<Accommodation> findAll() {
        return this.accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return this.accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> create(AccommodationCreateDto accommodationDto) {
        Host host = this.hostRepository.findById(accommodationDto.getHostId())
                .orElseThrow(() -> new HostNotFoundException(accommodationDto.getHostId()));

        Accommodation accommodation = new Accommodation(
                accommodationDto.getName(),
                accommodationDto.getCategory(),
                host,
                accommodationDto.getNumRooms()
        );

        return Optional.of(this.accommodationRepository.save(accommodation));
    }

    @Override
    public Optional<Accommodation> update(Long id, AccommodationCreateDto accommodationDto) {
        Accommodation accommodation = this.accommodationRepository.findById(id)
                .orElseThrow(() -> new AccommodationNotFoundException(id));

        Host host = this.hostRepository.findById(accommodationDto.getHostId())
                .orElseThrow(() -> new HostNotFoundException(accommodationDto.getHostId()));

        accommodation.setName(accommodationDto.getName());
        accommodation.setCategory(accommodationDto.getCategory());
        accommodation.setHost(host);
        accommodation.setNumRooms(accommodationDto.getNumRooms());

        return Optional.of(this.accommodationRepository.save(accommodation));
    }

    @Override
    public Optional<Accommodation> markAsRented(Long id) {
        Accommodation accommodation = this.accommodationRepository.findById(id)
                .orElseThrow(() -> new AccommodationNotFoundException(id));

        accommodation.setIsRented(true);
        return Optional.of(this.accommodationRepository.save(accommodation));
    }

    @Override
    public void deleteById(Long id) {
        this.accommodationRepository.deleteById(id);
    }

    @Override
    public AccommodationDto toDto(Accommodation accommodation) {
        return new AccommodationDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                this.hostService.toDto(accommodation.getHost()),
                accommodation.getNumRooms(),
                accommodation.getIsRented()
        );
    }
}
