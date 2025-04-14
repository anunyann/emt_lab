package mk.ukim.finki.emt2025.repository;

import mk.ukim.finki.emt2025.model.domain.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    List<Accommodation> findByHostId(Long hostId);
    List<Accommodation> findByIsRented(Boolean isRented);
}