package mk.ukim.finki.emt2025.web;

import mk.ukim.finki.emt2025.model.Accommodation;
import mk.ukim.finki.emt2025.model.dto.AccommodationCreateDto;
import mk.ukim.finki.emt2025.model.dto.AccommodationDto;
import mk.ukim.finki.emt2025.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationRestController {

    private final AccommodationService accommodationService;

    public AccommodationRestController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public List<AccommodationDto> getAllAccommodations() {
        return this.accommodationService.findAll()
                .stream()
                .map(this.accommodationService::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccommodationDto> getAccommodation(@PathVariable Long id) {
        return this.accommodationService.findById(id)
                .map(accommodation -> ResponseEntity.ok(this.accommodationService.toDto(accommodation)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AccommodationDto> createAccommodation(@RequestBody AccommodationCreateDto accommodationCreateDto) {
        return this.accommodationService.create(accommodationCreateDto)
                .map(accommodation -> ResponseEntity.ok(this.accommodationService.toDto(accommodation)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccommodationDto> updateAccommodation(@PathVariable Long id,
                                                                @RequestBody AccommodationCreateDto accommodationCreateDto) {
        return this.accommodationService.update(id, accommodationCreateDto)
                .map(accommodation -> ResponseEntity.ok(this.accommodationService.toDto(accommodation)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}/rent")
    public ResponseEntity<AccommodationDto> markAccommodationAsRented(@PathVariable Long id) {
        return this.accommodationService.markAsRented(id)
                .map(accommodation -> ResponseEntity.ok(this.accommodationService.toDto(accommodation)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {
        this.accommodationService.deleteById(id);
        if(this.accommodationService.findById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
