package aplbackfase2.interfaces.gateways;

import aplbackfase2.entities.PedidoFila;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IFilaRepositoryPort {

    Long inserir(PedidoFila pedidoFila);

    Optional<PedidoFila> obterPorNumeroNaFila(Long numero);

    Optional<PedidoFila> obterPorIdPedido(UUID idPedido);

    List<PedidoFila> obterPedidos(int page, int size);
}
