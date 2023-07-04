package aplbackfase1.domain.ports.in;

import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.PedidoProduto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPedidoUseCasePort {
    Pedido cadastrar(Pedido pedido);
    Pedido adicionarProduto(PedidoProduto produto);
    List<Pedido> buscarTodos(int pageNumber, int pageSize);
    Optional<Pedido> buscarPorId(UUID idPedido);
    List<Pedido> buscarPedidosPorCliente(UUID idCliente);
    List<Pedido> buscarPedidosPorStatus(StatusPedido statusPedido);
    List<Pedido> buscarPedidosPorClienteEStatus(UUID idCliente, StatusPedido statusPedido);
    Pedido atualizar(Pedido pedido);
    void remover(UUID idPedido);
    Pedido checkout(UUID idPedido);
}
