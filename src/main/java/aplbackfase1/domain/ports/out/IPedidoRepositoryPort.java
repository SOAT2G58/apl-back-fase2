package aplbackfase1.domain.ports.out;

import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.PedidoProduto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPedidoRepositoryPort {
    Pedido cadastrar(Pedido pedido);
    Pedido adicionarProduto(PedidoProduto produto);
    List<Pedido> buscarTodos(int pageNumber, int pageSize);
    Optional<Pedido> buscarPorId(UUID id);
    List<Pedido> buscarPedidosPorCliente(UUID idCliente);
    List<Pedido> buscarPedidosPorStatus(StatusPedido statusPedido);
    Pedido atualizar(Pedido pedido);
    void remover(UUID id);
    Pedido checkout(UUID idPedido);
}
