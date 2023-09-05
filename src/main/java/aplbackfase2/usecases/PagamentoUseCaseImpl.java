package aplbackfase2.usecases;


import aplbackfase2.utils.enums.StatusPagamento;
import aplbackfase2.exceptions.entities.PedidoInvalidoException;
import aplbackfase2.exceptions.entities.PedidoNaoEncontradoException;
import aplbackfase2.interfaces.usecases.IPagamentoUseCase;
import aplbackfase2.interfaces.usecases.IPedidoUseCasePort;
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
        // TODO - Colocar o pedido na fila com status RECEBIDO
        return true;
    }

    @Override
    public StatusPagamento localizarStatusPagamento(UUID idPedido, IPedidoUseCasePort pedidoUseCasePort) throws PedidoInvalidoException, PedidoNaoEncontradoException  {
        if (Objects.isNull(idPedido)) {
            throw new PedidoInvalidoException();
        }
        return pedidoUseCasePort.buscarPorId(idPedido).orElseThrow(() -> new PedidoNaoEncontradoException()).getStatusPagamento();
    }
}
