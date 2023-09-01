package aplbackfase2.entities;

import lombok.*;

import java.util.UUID;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoFila {

    private UUID idPedido;
    private Long numeroNaFila;

    public PedidoFila (Pedido pedido) {
        this.idPedido = pedido.getIdPedido();
    }

}
