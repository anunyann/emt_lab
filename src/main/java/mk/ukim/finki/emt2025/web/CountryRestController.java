package mk.ukim.finki.emt2025.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt2025.model.dto.CountryCreateDto;
import mk.ukim.finki.emt2025.model.dto.CountryDto;
import mk.ukim.finki.emt2025.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Country API", description = "Endpoints for managing countries")
public class CountryRestController {

    private final CountryApplicationService countryService;

    public CountryRestController(CountryApplicationService countryService) {
        this.countryService = countryService;
    }

    @Operation(summary = "Get all countries", description = "Retrieves a list of all countries")
    @ApiResponse(responseCode = "200", description = "List of countries retrieved successfully")
    @GetMapping
    public List<CountryDto> getAllCountries() {
        return this.countryService.findAll();
    }

    @Operation(summary = "Get country by ID", description = "Retrieves a country by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country found"),
            @ApiResponse(responseCode = "404", description = "Country not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getCountry(@PathVariable Long id) {
        return this.countryService.findById(id)
                .map(country -> ResponseEntity.ok(country))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new country", description = "Creates a new country with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid country data")
    })
    @PostMapping
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<CountryDto> createCountry(@RequestBody CountryCreateDto countryCreateDto) {
        return this.countryService.create(countryCreateDto)
                .map(country -> ResponseEntity.ok(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update a country", description = "Updates an existing country with new details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid country data"),
            @ApiResponse(responseCode = "404", description = "Country not found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<CountryDto> updateCountry(@PathVariable Long id,
                                                    @RequestBody CountryCreateDto countryCreateDto) {
        return this.countryService.update(id, countryCreateDto)
                .map(country -> ResponseEntity.ok(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Delete a country", description = "Deletes a country by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Country not found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        this.countryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}