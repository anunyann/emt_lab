package mk.ukim.finki.emt2025.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Immutable
@Table(name = "hosts_per_country", schema = "public")
@Subselect("SELECT * FROM accommodations_per_host")
public class AccommodationsPerHostView {

    @Id
    @Column(name = "host_id")
    private Long hostId;

    @Column(name = "host_name")
    private String hostName;

    @Column(name = "host_surname")
    private String hostSurname;

    @Column(name = "accommodation_count")
    private Integer accommodationCount;
}