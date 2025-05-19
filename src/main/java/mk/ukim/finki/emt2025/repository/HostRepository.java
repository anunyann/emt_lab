package mk.ukim.finki.emt2025.repository;

import mk.ukim.finki.emt2025.model.domain.Host;
import mk.ukim.finki.emt2025.model.projections.HostNameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
    List<Host> findByCountryId(Long countryId);
    @Query("SELECT h.name as name, h.surname as surname FROM Host h")
    List<HostNameProjection> findAllHostNames();
}