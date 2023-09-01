package aplbackfase2.interfaces.gateways;

import aplbackfase2.utils.enums.StatusPedido;
import aplbackfase2.exceptions.entities.PedidoNaoEncontradoException;
import aplbackfase2.entities.Pedido;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPedidoRepositoryPort {
    Pedido cadastrar(Pedido pedido);
    Pedido atualizar(Pedido pedido);
    Pedido atualizarStatus(StatusPedido status, UUID idPedido) throws PedidoNaoEncontradoException;
    void remover(UUID idPedido);
    List<Pedido> buscarTodos(int pageNumber, int pageSize);
    Optional<Pedido> buscarPorId(UUID idPedido);
    List<Pedido> buscarPedidosPorCliente(UUID idCliente);
    List<Pedido> buscarPedidosPorStatus(StatusPedido statusPedido);
    List<Pedido> buscarPedidosPorClienteEStatus(UUID idCliente, StatusPedido statusPedido);
    Pedido checkout(UUID idPedido);
}
