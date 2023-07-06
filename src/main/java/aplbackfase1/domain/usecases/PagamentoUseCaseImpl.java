package aplbackfase1.domain.usecases;


import aplbackfase1.domain.exceptions.PedidoInvalidoException;
import aplbackfase1.domain.ports.in.IPagamentoUseCase;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
public class PagamentoUseCaseImpl implements IPagamentoUseCase {

    @Override
    public boolean realizarPagamento(UUID idPedido) throws PedidoInvalidoException {
        if (Objects.isNull(idPedido)) {
            throw new PedidoInvalidoException();
        }
        return true;
    }
}
