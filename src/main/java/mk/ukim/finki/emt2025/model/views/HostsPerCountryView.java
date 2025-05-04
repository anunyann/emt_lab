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
@Table(name = "hosts_per_country", schema = "public")
@Immutable
@Subselect("SELECT * FROM hosts_per_country")
public class HostsPerCountryView {

    @Id
    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "continent")
    private String continent;

    @Column(name = "host_count")
    private Integer hostCount;
}