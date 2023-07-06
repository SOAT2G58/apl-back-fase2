package aplbackfase1.domain.ports.out;

import aplbackfase1.domain.model.PedidoFila;

import java.util.Optional;
import java.util.UUID;

public interface IFilaRepositoryPort {

    Long inserir(PedidoFila pedidoFila);

    Optional<PedidoFila> obterPorNumeroNaFila(Long numero);

    Optional<PedidoFila> obterPorIdPedido(UUID idPedido);
}
