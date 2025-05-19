package mk.ukim.finki.emt2025.repository;

import mk.ukim.finki.emt2025.model.views.AccommodationsPerHostView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccommodationsPerHostViewRepository extends JpaRepository<AccommodationsPerHostView, Long> {

    @Transactional
    @Modifying
    @Query(value = "REFRESH MATERIALIZED VIEW accommodations_per_host", nativeQuery = true)
    void refreshMaterializedView();
}