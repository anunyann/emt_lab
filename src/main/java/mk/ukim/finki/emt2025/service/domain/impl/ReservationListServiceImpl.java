package mk.ukim.finki.emt2025.service.domain.impl;

import mk.ukim.finki.emt2025.model.domain.Accommodation;
import mk.ukim.finki.emt2025.model.domain.ReservationList;
import mk.ukim.finki.emt2025.model.domain.User;
import mk.ukim.finki.emt2025.model.enumerations.ReservationStatus;
import mk.ukim.finki.emt2025.model.exceptions.AccommodationAlreadyReservedException;
import mk.ukim.finki.emt2025.model.exceptions.AccommodationNotFoundException;
import mk.ukim.finki.emt2025.model.exceptions.ReservationListNotFoundException;
import mk.ukim.finki.emt2025.repository.AccommodationRepository;
import mk.ukim.finki.emt2025.repository.ReservationListRepository;
import mk.ukim.finki.emt2025.service.domain.AccommodationService;
import mk.ukim.finki.emt2025.service.domain.ReservationListService;
import mk.ukim.finki.emt2025.service.domain.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationListServiceImpl implements ReservationListService {

    private final ReservationListRepository reservationListRepository;
    private final UserService userService;
    private final AccommodationService accommodationService;
    private final AccommodationRepository accommodationRepository;

    public ReservationListServiceImpl(
            ReservationListRepository reservationListRepository,
            UserService userService,
            AccommodationService accommodationService,
            AccommodationRepository accommodationRepository) {
        this.reservationListRepository = reservationListRepository;
        this.userService = userService;
        this.accommodationService = accommodationService;
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public List<Accommodation> listAllAccommodationsInReservationList(Long listId) {
        ReservationList reservationList = this.reservationListRepository.findById(listId)
                .orElseThrow(() -> new ReservationListNotFoundException(listId));
        return reservationList.getAccommodations();
    }

    @Override
    public Optional<ReservationList> getActiveReservationList(String username) {
        User user = this.userService.findByUsername(username);

        return this.reservationListRepository.findByUserAndStatus(user, ReservationStatus.PENDING)
                .or(() -> {
                    ReservationList newReservationList = new ReservationList(user);
                    return Optional.of(this.reservationListRepository.save(newReservationList));
                });
    }

    @Override
    public Optional<ReservationList> addAccommodationToReservationList(String username, Long accommodationId) {
        ReservationList reservationList = this.getActiveReservationList(username)
                .orElseThrow(() -> new RuntimeException("Could not create reservation list"));

        Accommodation accommodation = this.accommodationService.findById(accommodationId)
                .orElseThrow(() -> new AccommodationNotFoundException(accommodationId));

        if (accommodation.getIsRented()) {
            throw new AccommodationAlreadyReservedException(accommodationId);
        }

        if (!reservationList.getAccommodations().contains(accommodation)) {
            reservationList.getAccommodations().add(accommodation);
            reservationList = this.reservationListRepository.save(reservationList);
        }

        return Optional.of(reservationList);
    }

    @Override
    @Transactional
    public Optional<ReservationList> confirmReservation(Long reservationListId) {
        ReservationList reservationList = this.reservationListRepository.findById(reservationListId)
                .orElseThrow(() -> new ReservationListNotFoundException(reservationListId));

        // Mark all accommodations as rented
        reservationList.getAccommodations().forEach(accommodation -> {
            accommodation.setIsRented(true);
            this.accommodationRepository.save(accommodation);
        });

        // Change status to CONFIRMED
        reservationList.setStatus(ReservationStatus.CONFIRMED);
        return Optional.of(this.reservationListRepository.save(reservationList));
    }

    @Override
    public void clearReservationList(Long reservationListId) {
        ReservationList reservationList = this.reservationListRepository.findById(reservationListId)
                .orElseThrow(() -> new ReservationListNotFoundException(reservationListId));

        reservationList.getAccommodations().clear();
        this.reservationListRepository.save(reservationList);
    }
}