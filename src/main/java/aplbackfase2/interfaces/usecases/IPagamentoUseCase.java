package aplbackfase2.interfaces.usecases;

import aplbackfase2.utils.enums.StatusPagamento;

import java.util.UUID;

public interface IPagamentoUseCase {
    boolean realizarPagamento(UUID idPedido);
    StatusPagamento localizarStatusPagamento(UUID idPedido, IPedidoUseCasePort pedidoUseCasePort);
}
