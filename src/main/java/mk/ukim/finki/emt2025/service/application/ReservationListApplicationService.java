package mk.ukim.finki.emt2025.service.application;

import mk.ukim.finki.emt2025.model.dto.AccommodationDto;
import mk.ukim.finki.emt2025.model.dto.ReservationListDto;

import java.util.List;
import java.util.Optional;

public interface ReservationListApplicationService {
    List<AccommodationDto> listAllAccommodationsInReservationList(Long listId);
    Optional<ReservationListDto> getActiveReservationList(String username);
    Optional<ReservationListDto> addAccommodationToReservationList(String username, Long accommodationId);
    Optional<ReservationListDto> confirmReservation(Long reservationListId);
    void clearReservationList(Long reservationListId);
}