
package mk.ukim.finki.emt2025.repository;

import mk.ukim.finki.emt2025.model.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {})
    List<User> findAll();
}