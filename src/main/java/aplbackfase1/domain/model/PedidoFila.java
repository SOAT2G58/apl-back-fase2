package aplbackfase1.domain.model;

import lombok.*;

import java.util.UUID;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoFila {

    // Podemos passar o pedido inteiro, mas se tivermos o ID, podemos
    // recuperar o pedido inteiro, então acho que só o ID é suficiente
    private UUID idPedido;
    private Long numeroNaFila;
}
