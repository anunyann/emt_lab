package mk.ukim.finki.emt2025.web;

import mk.ukim.finki.emt2025.model.domain.User;
import mk.ukim.finki.emt2025.model.dto.AccommodationDto;
import mk.ukim.finki.emt2025.model.dto.ReservationListDto;
import mk.ukim.finki.emt2025.service.application.ReservationListApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationListRestController {

    private final ReservationListApplicationService reservationListService;

    public ReservationListRestController(ReservationListApplicationService reservationListService) {
        this.reservationListService = reservationListService;
    }

    @GetMapping
    public ResponseEntity<ReservationListDto> getActiveReservationList(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }

        User user = (User) authentication.getPrincipal();
        return this.reservationListService.getActiveReservationList(user.getUsername())
                .map(reservationList -> ResponseEntity.ok(reservationList))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/accommodations")
    public List<AccommodationDto> listAccommodationsInReservation(@PathVariable Long id) {
        return this.reservationListService.listAllAccommodationsInReservationList(id);
    }

    @PostMapping("/add-accommodation/{id}")
    public ResponseEntity<ReservationListDto> addAccommodationToReservation(
            @PathVariable Long id,
            Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }

        User user = (User) authentication.getPrincipal();
        try {
            return this.reservationListService.addAccommodationToReservationList(user.getUsername(), id)
                    .map(reservationList -> ResponseEntity.ok(reservationList))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/confirm")
    public ResponseEntity<ReservationListDto> confirmReservation(@PathVariable Long id) {
        return this.reservationListService.confirmReservation(id)
                .map(reservationList -> ResponseEntity.ok(reservationList))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/clear")
    public ResponseEntity<Void> clearReservationList(@PathVariable Long id) {
        this.reservationListService.clearReservationList(id);
        return ResponseEntity.ok().build();
    }
}