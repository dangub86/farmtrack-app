package farmtrack.hibernate.repository;

import farmtrack.hibernate.domain.TipoVoce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoVoceRepository extends JpaRepository<TipoVoce, Integer> {
    TipoVoce findByName(String name);
}

