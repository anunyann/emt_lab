import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt2025.model.Country;
import mk.ukim.finki.emt2025.model.Host;
import mk.ukim.finki.emt2025.model.dto.AccommodationCreateDto;
import mk.ukim.finki.emt2025.model.AccommodationCategory;
import mk.ukim.finki.emt2025.repository.CountryRepository;
import mk.ukim.finki.emt2025.repository.HostRepository;
import mk.ukim.finki.emt2025.service.AccommodationService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final AccommodationService accommodationService;

    public DataInitializer(CountryRepository countryRepository,
                           HostRepository hostRepository,
                           AccommodationService accommodationService) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.accommodationService = accommodationService;
    }

    @PostConstruct
    public void initData() {
        if (this.countryRepository.count() == 0 && this.hostRepository.count() == 0) {
            // Create countries
            Country usa = this.countryRepository.save(new Country("USA", "North America"));
            Country france = this.countryRepository.save(new Country("France", "Europe"));
            Country japan = this.countryRepository.save(new Country("Japan", "Asia"));

            // Create hosts
            Host johnDoe = this.hostRepository.save(new Host("John", "Doe", usa));
            Host janeDoe = this.hostRepository.save(new Host("Jane", "Doe", france));
            Host akiraYamamoto = this.hostRepository.save(new Host("Akira", "Yamamoto", japan));

            // Create accommodations
            this.accommodationService.create(new AccommodationCreateDto(
                    "Beachfront Villa",
                    AccommodationCategory.HOUSE,
                    johnDoe.getId(),
                    5
            ));

            this.accommodationService.create(new AccommodationCreateDto(
                    "Paris Apartment",
                    AccommodationCategory.APARTMENT,
                    janeDoe.getId(),
                    2
            ));

            this.accommodationService.create(new AccommodationCreateDto(
                    "Traditional Ryokan",
                    AccommodationCategory.HOTEL,
                    akiraYamamoto.getId(),
                    8
            ));

            this.accommodationService.create(new AccommodationCreateDto(
                    "Single Room in Downtown",
                    AccommodationCategory.ROOM,
                    johnDoe.getId(),
                    1
            ));
        }
    }
}