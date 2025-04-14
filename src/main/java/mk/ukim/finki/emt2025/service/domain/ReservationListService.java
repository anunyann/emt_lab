
package mk.ukim.finki.emt2025.service.domain;

import mk.ukim.finki.emt2025.model.domain.Accommodation;
import mk.ukim.finki.emt2025.model.domain.ReservationList;

import java.util.List;
import java.util.Optional;

public interface ReservationListService {
    List<Accommodation> listAllAccommodationsInReservationList(Long listId);
    Optional<ReservationList> getActiveReservationList(String username);
    Optional<ReservationList> addAccommodationToReservationList(String username, Long accommodationId);
    Optional<ReservationList> confirmReservation(Long reservationListId);
    void clearReservationList(Long reservationListId);
}