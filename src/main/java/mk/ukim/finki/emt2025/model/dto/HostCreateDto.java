package mk.ukim.finki.emt2025.model.dto;

import lombok.Data;

@Data
public class HostCreateDto {
    private String name;
    private String surname;
    private Long countryId;

    public HostCreateDto() {
    }

    public HostCreateDto(String name, String surname, Long countryId) {
        this.name = name;
        this.surname = surname;
        this.countryId = countryId;
    }
}
