package aplbackfase1.domain.usecases;


import aplbackfase1.domain.enums.StatusPagamento;
import aplbackfase1.domain.exceptions.PedidoInvalidoException;
import aplbackfase1.domain.exceptions.PedidoNaoEncontradoException;
import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.ports.in.IPagamentoUseCase;
import aplbackfase1.domain.ports.in.IPedidoUseCasePort;
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
