package farmtrack.hibernate.repository;

import farmtrack.hibernate.domain.Credenziali;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Credenziali, Integer> {
}