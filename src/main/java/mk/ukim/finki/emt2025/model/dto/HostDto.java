package mk.ukim.finki.emt2025.model.dto;


import lombok.Data;

@Data
public class HostDto {
    private Long id;
    private String name;
    private String surname;
    private CountryDto country;

    public HostDto() {
    }

    public HostDto(Long id, String name, String surname, CountryDto country) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
