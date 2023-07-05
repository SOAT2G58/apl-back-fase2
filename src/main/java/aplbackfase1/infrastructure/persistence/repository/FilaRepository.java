package aplbackfase1.infrastructure.persistence.repository;

import aplbackfase1.domain.model.PedidoFila;
import aplbackfase1.infrastructure.persistence.entity.PedidoFilaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilaRepository extends JpaRepository<PedidoFilaEntity, Long> {
}
