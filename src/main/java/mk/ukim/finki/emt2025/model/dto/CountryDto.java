package mk.ukim.finki.emt2025.model.dto;

import lombok.Data;

@Data
public class CountryDto {
    private Long id;
    private String name;
    private String continent;

    public CountryDto() {
    }

    public CountryDto(Long id, String name, String continent) {
        this.id = id;
        this.name = name;
        this.continent = continent;
    }
}
