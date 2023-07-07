package aplbackfase1.domain.ports.in;

import java.util.UUID;

public interface IPagamentoUseCase {
    boolean realizarPagamento(UUID idPedido);
}
