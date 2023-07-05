package aplbackfase1.domain.model;

import aplbackfase1.domain.enums.StatusPedido;
import lombok.*;

import java.util.UUID;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoFila {

    private Pedido pedido;
    private Long numeroNaFila;

    public PedidoFila (Pedido pedido) {
        this.pedido = pedido;
        this.pedido.atualizaStatusPedido(StatusPedido.R);
    }

    public void atualizaStatusPedido(StatusPedido novoStatus) {
        this.pedido.atualizaStatusPedido(novoStatus);
    }

}
