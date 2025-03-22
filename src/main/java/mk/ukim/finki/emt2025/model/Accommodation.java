package mk.ukim.finki.emt2025.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt2025.model.AccommodationCategory;

@Data
@Entity
@NoArgsConstructor
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private AccommodationCategory category;

    @ManyToOne
    private Host host;

    private Integer numRooms;

    private Boolean isRented;

    public Accommodation(String name, AccommodationCategory category, Host host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.isRented = false;
    }
}
