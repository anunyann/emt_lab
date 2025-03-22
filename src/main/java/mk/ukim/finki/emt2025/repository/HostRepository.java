package mk.ukim.finki.emt2025.repository;

import mk.ukim.finki.emt2025.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
}
