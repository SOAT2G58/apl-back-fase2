package aplbackfase1.domain.ports.in;

import aplbackfase1.domain.enums.StatusPagamento;

import java.util.UUID;

public interface IPagamentoUseCase {
    boolean realizarPagamento(UUID idPedido);
    StatusPagamento localizarStatusPagamento(UUID idPedido, IPedidoUseCasePort pedidoUseCasePort);
}
