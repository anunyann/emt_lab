package mk.ukim.finki.emt2025.model.dto;


import lombok.Data;

@Data
public class CountryCreateDto {
    private String name;
    private String continent;

    public CountryCreateDto() {
    }

    public CountryCreateDto(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }
}