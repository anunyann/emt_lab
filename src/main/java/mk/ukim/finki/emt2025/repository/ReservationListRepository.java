package mk.ukim.finki.emt2025.repository;

import mk.ukim.finki.emt2025.model.domain.ReservationList;
import mk.ukim.finki.emt2025.model.domain.User;
import mk.ukim.finki.emt2025.model.enumerations.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationListRepository extends JpaRepository<ReservationList, Long> {
    List<ReservationList> findByUser(User user);
    Optional<ReservationList> findByUserAndStatus(User user, ReservationStatus status);
}