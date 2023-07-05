package aplbackfase1.domain.ports.in;

import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.PedidoFila;

public interface IPedidoFilaUseCasePort {

    public PedidoFila inserirPedidoNaFila(Pedido pedido);

}
