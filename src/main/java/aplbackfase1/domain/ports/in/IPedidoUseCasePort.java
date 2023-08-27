package aplbackfase1.domain.ports.in;

import aplbackfase1.domain.enums.StatusPagamento;
import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.domain.exceptions.PedidoNaoEncontradoException;
import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.PedidoProduto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPedidoUseCasePort {
    Pedido cadastrar(Pedido pedido);
    Pedido atualizar(Pedido pedido);
    Pedido atualizarStatus(StatusPedido status, UUID idPedido) throws PedidoNaoEncontradoException;
    Pedido atualizarStatusPagamento(StatusPagamento status, UUID idPedido) throws PedidoNaoEncontradoException;
    void remover(UUID idPedido);
    List<Pedido> buscarTodos(int pageNumber, int pageSize);
    Optional<Pedido> buscarPorId(UUID idPedido);
    List<Pedido> buscarPedidosPorCliente(UUID idCliente);
    List<Pedido> buscarPedidosPorStatus(StatusPedido statusPedido);
    List<Pedido> buscarPedidosPorClienteEStatus(UUID idCliente, StatusPedido statusPedido);

    Optional<PedidoProduto> buscarPedidoProdutoPorId(UUID id);

    Pedido checkout(UUID idPedido);
}
