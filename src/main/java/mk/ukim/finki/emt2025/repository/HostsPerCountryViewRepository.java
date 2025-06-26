package mk.ukim.finki.emt2025.repository;

import mk.ukim.finki.emt2025.model.views.HostsPerCountryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HostsPerCountryViewRepository extends JpaRepository<HostsPerCountryView, Long> {

    @Transactional
    @Modifying
    @Query(value = "REFRESH MATERIALIZED VIEW hosts_per_country", nativeQuery = true)
    void refreshMaterializedView();
}