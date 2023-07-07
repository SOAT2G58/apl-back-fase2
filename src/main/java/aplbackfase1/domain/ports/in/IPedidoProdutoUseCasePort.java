package aplbackfase1.domain.ports.in;


import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.PedidoProduto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPedidoProdutoUseCasePort {

    Optional<PedidoProduto> buscarPorId(UUID id);
    List<PedidoProduto> buscarPorPedido(Pedido pedido);
    PedidoProduto adicionarPedidoProduto(PedidoProduto pedidoProduto);
    PedidoProduto editarPedidoProduto(PedidoProduto pedidoProduto);
    void  deletarPedidoProduto(UUID idPedidoProduto);
}
