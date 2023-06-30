package aplbackfase1.infrastructure.persistence.repository;

import aplbackfase1.infrastructure.persistence.entity.ClienteEntity;
import aplbackfase1.domain.model.valueObject.Cpf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, UUID> {
    Optional<ClienteEntity> findAllByCpf(Cpf cpf);
    Optional<ClienteEntity> findById(UUID uuid);
}
