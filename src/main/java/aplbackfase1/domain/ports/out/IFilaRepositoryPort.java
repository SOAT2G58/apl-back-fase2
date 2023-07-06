package aplbackfase1.domain.ports.out;

import aplbackfase1.domain.model.PedidoFila;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IFilaRepositoryPort {

    Long inserir(PedidoFila pedidoFila);

    Optional<PedidoFila> obterPorNumeroNaFila(Long numero);

    Optional<PedidoFila> obterPorIdPedido(UUID idPedido);

    Page<PedidoFila> obterPedidos(Pageable paginacao);
}
