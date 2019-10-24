package farmtrack.hibernate.repository;

import farmtrack.hibernate.domain.Voce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BilancioRepository extends JpaRepository<Voce, Integer> {
}