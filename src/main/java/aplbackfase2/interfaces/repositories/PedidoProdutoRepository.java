package aplbackfase2.interfaces.repositories;

import aplbackfase2.entities.Pedido;
import aplbackfase2.gateways.entities.PedidoProdutoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProdutoEntity, UUID> {

    List<PedidoProdutoEntity> findByPedido(Pedido pedido);

}
