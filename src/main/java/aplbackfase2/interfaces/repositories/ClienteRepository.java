package aplbackfase2.interfaces.repositories;

import aplbackfase2.entities.Cpf;
import aplbackfase2.gateways.entities.ClienteEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, UUID> {
    Optional<ClienteEntity> findAllByCpf(Cpf cpf);
    Optional<ClienteEntity> findById(UUID uuid);
}
