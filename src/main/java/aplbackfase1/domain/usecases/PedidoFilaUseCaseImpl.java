package aplbackfase1.domain.usecases;

import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.PedidoFila;
import aplbackfase1.domain.ports.in.IPedidoFilaUseCasePort;
import aplbackfase1.domain.ports.out.IFilaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoFilaUseCaseImpl implements IPedidoFilaUseCasePort {

    @Autowired
    private IFilaRepositoryPort filaRepositoryPort;

    // Pedido já tem que ter sido salvo no banco e ter seu ID gerado
    @Override
    public PedidoFila inserirPedidoNaFila(Pedido pedido) {
        if (pedido.getStatusPedido() == StatusPedido.A) {
            //TODO confirmar se o pedido já existe na fila, se existir, retornar o existente ou exception
            var pedidoFila = new PedidoFila(pedido);
            var numeroNaFila = filaRepositoryPort.inserir(pedidoFila);
            pedidoFila.setNumeroNaFila(numeroNaFila);
            System.out.println("Pedido: " + pedido.getIdPedido());
            System.out.println("em status: " + pedido.getStatus());
            System.out.println("numero na fila: " + numeroNaFila);
            return pedidoFila;
//            System.out.println("Pedido: " + pedido.getIdPedido() + " em status: " + pedido.getStatus());
//            return new PedidoFila(UUID.fromString("68115b28-d71f-43c2-87ba-876185927927"), 1L);
        } else {
            //TODO throw exception pedido com status já processado
            System.out.println("Exception");
            return null;
        }
    }
}
