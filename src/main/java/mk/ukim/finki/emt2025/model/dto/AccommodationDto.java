package mk.ukim.finki.emt2025.model.dto;

import lombok.Data;
import mk.ukim.finki.emt2025.model.AccommodationCategory;

@Data
public class AccommodationDto {
    private Long id;
    private String name;
    private AccommodationCategory category;
    private HostDto host;
    private Integer numRooms;
    private Boolean isRented;

    public AccommodationDto() {
    }

    public AccommodationDto(Long id, String name, AccommodationCategory category, HostDto host, Integer numRooms, Boolean isRented) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.isRented = isRented;
    }
}
