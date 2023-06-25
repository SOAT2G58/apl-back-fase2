package aplbackfase1.domain.ports.in;

import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.Produto;

import java.util.List;
import java.util.UUID;

public interface IPedidoService {
    Pedido criarPedido(UUID idCliente);
    Pedido incluirItem(UUID idCliente, UUID idProduto);
    Pedido removerItem(UUID idCliente, UUID idProduto);
    Pedido finalizarPedido(UUID idCliente);
    List<Pedido> listarPedidos(UUID idCliente);
}
