package aplbackfase2.interfaces.gateways;

import aplbackfase2.entities.Pedido;
import aplbackfase2.entities.PedidoProduto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPedidoProdutoRepositoryPort {

    List<PedidoProduto> buscarTodos();
    Optional<PedidoProduto> buscarPorId(UUID id);
    List<PedidoProduto> buscarPorPedido(Pedido pedido);
    PedidoProduto adicionarPedidoProduto(PedidoProduto pedidoProduto);
    PedidoProduto editarPedidoProduto(PedidoProduto pedidoProduto);
    void excluirPedidoProduto(UUID idProduto);
}
