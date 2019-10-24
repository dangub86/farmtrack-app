package farmtrack.hibernate.repository;

import farmtrack.hibernate.domain.Credenziali;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredenzialiRepository extends JpaRepository<Credenziali, Integer> {
    Credenziali findByPassword(String password);
}