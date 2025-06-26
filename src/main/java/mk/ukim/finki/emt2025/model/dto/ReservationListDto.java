package mk.ukim.finki.emt2025.model.dto;

import lombok.Data;
import mk.ukim.finki.emt2025.model.domain.ReservationList;
import mk.ukim.finki.emt2025.model.enumerations.ReservationStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ReservationListDto {
    private Long id;
    private LocalDateTime dateCreated;
    private UserDto user;
    private List<AccommodationDto> accommodations;
    private ReservationStatus status;

    public ReservationListDto() {
    }

    public ReservationListDto(Long id, LocalDateTime dateCreated, UserDto user, List<AccommodationDto> accommodations, ReservationStatus status) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.user = user;
        this.accommodations = accommodations;
        this.status = status;
    }

    public static ReservationListDto from(ReservationList reservationList, UserDto userDto, List<AccommodationDto> accommodationDtos) {
        return new ReservationListDto(
                reservationList.getId(),
                reservationList.getDateCreated(),
                userDto,
                accommodationDtos,
                reservationList.getStatus()
        );
    }
}