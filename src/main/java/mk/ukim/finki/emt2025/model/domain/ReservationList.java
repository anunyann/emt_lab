package mk.ukim.finki.emt2025.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emt2025.model.enumerations.ReservationStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ReservationList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany
    private List<Accommodation> accommodations;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public ReservationList() {
    }

    public ReservationList(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.accommodations = new ArrayList<>();
        this.status = ReservationStatus.PENDING;
    }
}