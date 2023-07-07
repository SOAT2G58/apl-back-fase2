package aplbackfase1.domain.ports.in;


import aplbackfase1.domain.model.PedidoProduto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPedidoProdutoUseCasePort {

    Optional<PedidoProduto> buscarPorId(UUID id);
    Optional<PedidoProduto> buscarIdPedido(UUID idPedido);
    PedidoProduto adicionarPedidoProduto(PedidoProduto pedidoProduto);
    PedidoProduto editarPedidoProduto(PedidoProduto pedidoProduto);
    void  deletarPedidoProduto(UUID idPedidoProduto);
}
