package mk.ukim.finki.emt2025.model.dto;

import lombok.Data;
import mk.ukim.finki.emt2025.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

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

    public static HostDto from(Host host, CountryDto countryDto) {
        return new HostDto(
                host.getId(),
                host.getName(),
                host.getSurname(),
                countryDto
        );
    }

    public static List<HostDto> from(List<Host> hosts, List<CountryDto> countryDtos) {
        return hosts.stream()
                .map(host -> {
                    CountryDto countryDto = countryDtos.stream()
                            .filter(c -> c.getId().equals(host.getCountry().getId()))
                            .findFirst()
                            .orElse(null);
                    return HostDto.from(host, countryDto);
                })
                .collect(Collectors.toList());
    }
}
