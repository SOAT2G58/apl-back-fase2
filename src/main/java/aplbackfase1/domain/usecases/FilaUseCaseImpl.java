package aplbackfase1.domain.usecases;

import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.PedidoFila;
import aplbackfase1.domain.ports.in.IFilaUseCasePort;
import aplbackfase1.domain.ports.out.IFilaRepositoryPort;

public class FilaUseCaseImpl implements IFilaUseCasePort {

    private IFilaRepositoryPort filaRepositoryPort;

    public FilaUseCaseImpl(IFilaRepositoryPort filaRepositoryPort) {
        this.filaRepositoryPort = filaRepositoryPort;
    }

    @Override
    public PedidoFila inserirPedidoNaFila(Pedido pedido) {
        if (pedido.getStatusPedido() == StatusPedido.A) {
            //TODO Pedido já tem que ter sido salvo no banco e ter seu ID gerado
            //TODO confirmar se o pedido já existe na fila, se existir, retornar o existente ou exception
            var pedidoFila = new PedidoFila(pedido);
            var numeroNaFila = filaRepositoryPort.inserir(pedidoFila);
            pedidoFila.setNumeroNaFila(numeroNaFila);
            return pedidoFila;
        } else {
            //TODO throw exception pedido com status já processado
            System.out.println("Exception");
            return null;
        }
    }
}
