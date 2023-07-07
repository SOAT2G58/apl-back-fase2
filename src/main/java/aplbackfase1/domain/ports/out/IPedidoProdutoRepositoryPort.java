package aplbackfase1.domain.ports.out;

import aplbackfase1.domain.model.PedidoProduto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPedidoProdutoRepositoryPort {

    List<PedidoProduto> buscarTodos();
    Optional<PedidoProduto> buscarPorId(UUID id);
    List<PedidoProduto> buscarIdPedido(UUID idPedido);
    PedidoProduto adicionarPedidoProduto(PedidoProduto pedidoProduto);
    PedidoProduto editarPedidoProduto(PedidoProduto pedidoProduto);
    void deletar(UUID idPedidoProduto);
}
