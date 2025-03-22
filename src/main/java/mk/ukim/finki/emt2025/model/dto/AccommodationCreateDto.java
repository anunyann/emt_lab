package mk.ukim.finki.emt2025.model.dto;


import lombok.Data;
import mk.ukim.finki.emt2025.model.AccommodationCategory;

@Data
public class AccommodationCreateDto {
    private String name;
    private AccommodationCategory category;
    private Long hostId;
    private Integer numRooms;

    public AccommodationCreateDto() {
    }

    public AccommodationCreateDto(String name, AccommodationCategory category, Long hostId, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.hostId = hostId;
        this.numRooms = numRooms;
    }
}
