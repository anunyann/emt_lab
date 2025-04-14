package mk.ukim.finki.emt2025.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt2025.model.dto.AccommodationCreateDto;
import mk.ukim.finki.emt2025.model.dto.AccommodationDto;
import mk.ukim.finki.emt2025.service.application.AccommodationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
@Tag(name = "Accommodation API", description = "Endpoints for managing accommodations")
public class AccommodationRestController {

    private final AccommodationApplicationService accommodationService;

    public AccommodationRestController(AccommodationApplicationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @Operation(summary = "Get all accommodations", description = "Retrieves a list of all accommodations")
    @ApiResponse(responseCode = "200", description = "List of accommodations retrieved successfully")
    @GetMapping
    public List<AccommodationDto> getAllAccommodations() {
        return this.accommodationService.findAll();
    }

    @Operation(summary = "Get available accommodations", description = "Retrieves a list of all available (not rented) accommodations")
    @ApiResponse(responseCode = "200", description = "List of available accommodations retrieved successfully")
    @GetMapping("/available")
    public List<AccommodationDto> getAvailableAccommodations() {
        return this.accommodationService.findAvailableAccommodations();
    }

    @Operation(summary = "Get accommodation by ID", description = "Retrieves an accommodation by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accommodation found"),
            @ApiResponse(responseCode = "404", description = "Accommodation not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AccommodationDto> getAccommodation(@PathVariable Long id) {
        return this.accommodationService.findById(id)
                .map(accommodation -> ResponseEntity.ok(accommodation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new accommodation", description = "Creates a new accommodation with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accommodation created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid accommodation data")
    })
    @PostMapping
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<AccommodationDto> createAccommodation(@RequestBody AccommodationCreateDto accommodationCreateDto) {
        return this.accommodationService.create(accommodationCreateDto)
                .map(accommodation -> ResponseEntity.ok(accommodation))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update an accommodation", description = "Updates an existing accommodation with new details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accommodation updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid accommodation data"),
            @ApiResponse(responseCode = "404", description = "Accommodation not found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<AccommodationDto> updateAccommodation(@PathVariable Long id,
                                                                @RequestBody AccommodationCreateDto accommodationCreateDto) {
        return this.accommodationService.update(id, accommodationCreateDto)
                .map(accommodation -> ResponseEntity.ok(accommodation))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Mark accommodation as rented", description = "Updates an accommodation status to rented")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accommodation marked as rented successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid operation"),
            @ApiResponse(responseCode = "404", description = "Accommodation not found")
    })
    @PutMapping("/{id}/rent")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AccommodationDto> markAccommodationAsRented(@PathVariable Long id) {
        return this.accommodationService.markAsRented(id)
                .map(accommodation -> ResponseEntity.ok(accommodation))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Delete an accommodation", description = "Deletes an accommodation by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accommodation deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid operation"),
            @ApiResponse(responseCode = "404", description = "Accommodation not found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {
        this.accommodationService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}