package aplbackfase1.domain.usecases;

import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.PedidoFila;
import aplbackfase1.domain.ports.in.IPedidoFilaUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoFilaUseCaseImpl implements IPedidoFilaUseCasePort {

    // Pedido já tem que ter sido salvo no banco e ter seu ID gerado
    @Override
    public PedidoFila inserirPedidoNaFila(Pedido pedido) {
        if (pedido.getStatusPedido() == StatusPedido.A) {
            // insere pedido na fila
            // altera status para recebido
            System.out.println("Pedido: " + pedido.getIdPedido() + " em status: " + pedido.getStatus());
            return new PedidoFila(UUID.fromString("68115b28-d71f-43c2-87ba-876185927927"), 1L);
        } else {
            //TODO throw exception pedido com status já processado
            System.out.println("Exception");
            return null;
        }
    }
}
