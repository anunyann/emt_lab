package mk.ukim.finki.emt2025.service.application.impl;

import mk.ukim.finki.emt2025.model.domain.Accommodation;
import mk.ukim.finki.emt2025.model.domain.ReservationList;
import mk.ukim.finki.emt2025.model.dto.AccommodationDto;
import mk.ukim.finki.emt2025.model.dto.ReservationListDto;
import mk.ukim.finki.emt2025.model.dto.UserDto;
import mk.ukim.finki.emt2025.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt2025.service.application.ReservationListApplicationService;
import mk.ukim.finki.emt2025.service.application.UserApplicationService;
import mk.ukim.finki.emt2025.service.domain.ReservationListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationListApplicationServiceImpl implements ReservationListApplicationService {

    private final ReservationListService reservationListService;
    private final UserApplicationService userApplicationService;
    private final AccommodationApplicationService accommodationApplicationService;

    public ReservationListApplicationServiceImpl(
            ReservationListService reservationListService,
            UserApplicationService userApplicationService,
            AccommodationApplicationService accommodationApplicationService) {
        this.reservationListService = reservationListService;
        this.userApplicationService = userApplicationService;
        this.accommodationApplicationService = accommodationApplicationService;
    }

    @Override
    public List<AccommodationDto> listAllAccommodationsInReservationList(Long listId) {
        List<Accommodation> accommodations = this.reservationListService.listAllAccommodationsInReservationList(listId);

        return accommodations.stream()
                .map(accommodation -> accommodationApplicationService.findById(accommodation.getId()).orElse(null))
                .filter(accommodationDto -> accommodationDto != null)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReservationListDto> getActiveReservationList(String username) {
        return this.reservationListService.getActiveReservationList(username)
                .map(reservationList -> {
                    UserDto userDto = this.userApplicationService.findByUsername(username).orElse(null);

                    List<AccommodationDto> accommodationDtos = reservationList.getAccommodations().stream()
                            .map(accommodation -> accommodationApplicationService.findById(accommodation.getId()).orElse(null))
                            .filter(accommodationDto -> accommodationDto != null)
                            .collect(Collectors.toList());

                    return ReservationListDto.from(reservationList, userDto, accommodationDtos);
                });
    }

    @Override
    public Optional<ReservationListDto> addAccommodationToReservationList(String username, Long accommodationId) {
        return this.reservationListService.addAccommodationToReservationList(username, accommodationId)
                .map(reservationList -> {
                    UserDto userDto = this.userApplicationService.findByUsername(username).orElse(null);

                    List<AccommodationDto> accommodationDtos = reservationList.getAccommodations().stream()
                            .map(accommodation -> accommodationApplicationService.findById(accommodation.getId()).orElse(null))
                            .filter(accommodationDto -> accommodationDto != null)
                            .collect(Collectors.toList());

                    return ReservationListDto.from(reservationList, userDto, accommodationDtos);
                });
    }

    @Override
    public Optional<ReservationListDto> confirmReservation(Long reservationListId) {
        return this.reservationListService.confirmReservation(reservationListId)
                .map(reservationList -> {
                    UserDto userDto = this.userApplicationService.findByUsername(reservationList.getUser().getUsername()).orElse(null);

                    List<AccommodationDto> accommodationDtos = reservationList.getAccommodations().stream()
                            .map(accommodation -> accommodationApplicationService.findById(accommodation.getId()).orElse(null))
                            .filter(accommodationDto -> accommodationDto != null)
                            .collect(Collectors.toList());

                    return ReservationListDto.from(reservationList, userDto, accommodationDtos);
                });
    }

    @Override
    public void clearReservationList(Long reservationListId) {
        this.reservationListService.clearReservationList(reservationListId);
    }
}