package aplbackfase1.infrastructure.persistence.repository;

import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.infrastructure.persistence.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, UUID> {
    Optional<PedidoEntity> findById(UUID id);

    List<PedidoEntity> findByClienteId(UUID idCliente);

    List<PedidoEntity> findByStatus(StatusPedido statusPedido);

    List<PedidoEntity> findByClienteIdAndStatus(UUID idCliente, StatusPedido statusPedido);
}
