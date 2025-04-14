package mk.ukim.finki.emt2025.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.emt2025.model.dto.AccommodationDto;
import mk.ukim.finki.emt2025.model.dto.ReservationListDto;
import mk.ukim.finki.emt2025.model.exceptions.AccommodationAlreadyReservedException;
import mk.ukim.finki.emt2025.service.application.ReservationListApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@Tag(name = "Reservation API", description = "Endpoints for managing reservation lists")
public class ReservationListRestController {

    private final ReservationListApplicationService reservationListService;

    public ReservationListRestController(ReservationListApplicationService reservationListService) {
        this.reservationListService = reservationListService;
    }

    @Operation(summary = "Get active reservation list", description = "Gets the current user's active reservation list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation list retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Reservation list not found")
    })
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ReservationListDto> getActiveReservationList(HttpServletRequest request) {
        String username = request.getRemoteUser();
        return this.reservationListService.getActiveReservationList(username)
                .map(reservationList -> ResponseEntity.ok(reservationList))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "List accommodations in reservation", description = "Lists all accommodations in a specific reservation list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accommodations listed successfully"),
            @ApiResponse(responseCode = "404", description = "Reservation list not found")
    })
    @GetMapping("/{id}/accommodations")
    @PreAuthorize("hasRole('USER')")
    public List<AccommodationDto> listAccommodationsInReservation(@PathVariable Long id) {
        return this.reservationListService.listAllAccommodationsInReservationList(id);
    }

    @Operation(summary = "Add accommodation to reservation", description = "Adds an accommodation to the user's active reservation list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accommodation added successfully"),
            @ApiResponse(responseCode = "400", description = "Accommodation already reserved"),
            @ApiResponse(responseCode = "404", description = "Accommodation or reservation list not found")
    })
    @PostMapping("/add-accommodation/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ReservationListDto> addAccommodationToReservation(
            @PathVariable Long id,
            HttpServletRequest request) {
        try {
            String username = request.getRemoteUser();
            return this.reservationListService.addAccommodationToReservationList(username, id)
                    .map(reservationList -> ResponseEntity.ok(reservationList))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (AccommodationAlreadyReservedException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Confirm reservation", description = "Confirms all accommodations in the reservation list as rented")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation confirmed successfully"),
            @ApiResponse(responseCode = "404", description = "Reservation list not found")
    })
    @PostMapping("/{id}/confirm")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ReservationListDto> confirmReservation(@PathVariable Long id) {
        return this.reservationListService.confirmReservation(id)
                .map(reservationList -> ResponseEntity.ok(reservationList))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Clear reservation list", description = "Removes all accommodations from a reservation list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation list cleared successfully"),
            @ApiResponse(responseCode = "404", description = "Reservation list not found")
    })
    @PostMapping("/{id}/clear")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> clearReservationList(@PathVariable Long id) {
        this.reservationListService.clearReservationList(id);
        return ResponseEntity.ok().build();
    }
}