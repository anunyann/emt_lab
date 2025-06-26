package mk.ukim.finki.emt2025.model.dto;

import lombok.Data;
import mk.ukim.finki.emt2025.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

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

    public static CountryDto from(Country country) {
        return new CountryDto(
                country.getId(),
                country.getName(),
                country.getContinent()
        );
    }

    public static List<CountryDto> from(List<Country> countries) {
        return countries.stream()
                .map(CountryDto::from)
                .collect(Collectors.toList());
    }
}
