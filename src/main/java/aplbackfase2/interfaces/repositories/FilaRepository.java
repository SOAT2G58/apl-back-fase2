package aplbackfase2.interfaces.repositories;

import aplbackfase2.entities.PedidoFila;
import aplbackfase2.gateways.entities.PedidoFilaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FilaRepository extends JpaRepository<PedidoFilaEntity, Long> {
    Optional<PedidoFilaEntity> findByNumeroNaFila(Long numero);

    Optional<PedidoFilaEntity> findByIdPedido(UUID idPedido);
}
