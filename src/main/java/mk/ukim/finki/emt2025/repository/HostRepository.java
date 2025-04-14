package mk.ukim.finki.emt2025.repository;

import mk.ukim.finki.emt2025.model.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
    List<Host> findByCountryId(Long countryId);
}