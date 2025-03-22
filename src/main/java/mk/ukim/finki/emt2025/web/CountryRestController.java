package mk.ukim.finki.emt2025.web;
import mk.ukim.finki.emt2025.model.dto.CountryCreateDto;
import mk.ukim.finki.emt2025.model.dto.CountryDto;
import mk.ukim.finki.emt2025.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/countries")
public class CountryRestController {

    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<CountryDto> getAllCountries() {
        return this.countryService.findAll()
                .stream()
                .map(this.countryService::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getCountry(@PathVariable Long id) {
        return this.countryService.findById(id)
                .map(country -> ResponseEntity.ok(this.countryService.toDto(country)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CountryDto> createCountry(@RequestBody CountryCreateDto countryCreateDto) {
        return this.countryService.create(countryCreateDto)
                .map(country -> ResponseEntity.ok(this.countryService.toDto(country)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryDto> updateCountry(@PathVariable Long id,
                                                    @RequestBody CountryCreateDto countryCreateDto) {
        return this.countryService.update(id, countryCreateDto)
                .map(country -> ResponseEntity.ok(this.countryService.toDto(country)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        this.countryService.deleteById(id);
        if (this.countryService.findById(id).isEmpty()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}