package aplbackfase1.infrastructure.persistence.repository;

import aplbackfase1.infrastructure.persistence.entity.PedidoProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProdutoEntity, UUID> {

    List<PedidoProdutoEntity> findByPedidoId(UUID idPedido);
    List<PedidoProdutoEntity> findByPedido_Id(UUID idPedido);
    List<PedidoProdutoEntity> findByIdPedido(UUID idPedido);

}
