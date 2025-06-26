package mk.ukim.finki.emt2025.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt2025.model.domain.Country;
import mk.ukim.finki.emt2025.model.domain.Host;
import mk.ukim.finki.emt2025.model.enumerations.AccommodationCategory;
import mk.ukim.finki.emt2025.model.enumerations.Role;
import mk.ukim.finki.emt2025.model.dto.AccommodationCreateDto;
import mk.ukim.finki.emt2025.repository.CountryRepository;
import mk.ukim.finki.emt2025.repository.HostRepository;
import mk.ukim.finki.emt2025.repository.ReservationListRepository;
import mk.ukim.finki.emt2025.repository.UserRepository;
import mk.ukim.finki.emt2025.service.domain.AccommodationService;
import mk.ukim.finki.emt2025.service.domain.UserService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final ReservationListRepository reservationListRepository;
    private final UserRepository userRepository;
    private final AccommodationService accommodationService;
    private final UserService userService;

    public DataInitializer(CountryRepository countryRepository,
                           HostRepository hostRepository,
                           ReservationListRepository reservationListRepository,
                           UserRepository userRepository,
                           AccommodationService accommodationService,
                           UserService userService) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.reservationListRepository = reservationListRepository;
        this.userRepository = userRepository;
        this.accommodationService = accommodationService;
        this.userService = userService;
    }

    @PostConstruct
    public void initData() {
        // 🧹 Delete all existing data in safe order
        reservationListRepository.deleteAll();
        accommodationService.deleteAll(); // Add this method if missing
        hostRepository.deleteAll();
        countryRepository.deleteAll();
        userRepository.deleteAll();

        // 🌍 Add countries
        Country usa = this.countryRepository.save(new Country("USA", "North America"));
        Country france = this.countryRepository.save(new Country("France", "Europe"));
        Country japan = this.countryRepository.save(new Country("Japan", "Asia"));

        // 🏨 Add hosts
        Host johnDoe = this.hostRepository.save(new Host("John", "Doe", usa));
        Host janeDoe = this.hostRepository.save(new Host("Jane", "Doe", france));
        Host akiraYamamoto = this.hostRepository.save(new Host("Akira", "Yamamoto", japan));

        // 🛏️ Add accommodations
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

        // 👥 Add users
        this.userService.register("user", "user", "user", "Regular", "User", Role.ROLE_USER);
        this.userService.register("host", "host", "host", "Admin", "Host", Role.ROLE_HOST);
    }
}
