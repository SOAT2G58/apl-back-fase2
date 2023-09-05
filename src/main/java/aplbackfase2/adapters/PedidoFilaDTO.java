package aplbackfase2.adapters;


import aplbackfase2.entities.PedidoFila;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PedidoFilaDTO {
    private Long numeroNaFila;

    private UUID idPedido;

    // TODO - quando a feat pedido estiver pronta, mapear o pedido para o pedidoDTO e retornar o pedido inteiro e não só o ID
    // private PedidoDTO pedidoDTO;

    public PedidoFilaDTO from(PedidoFila pedidoFila) {
        return PedidoFilaDTO.builder()
                .numeroNaFila(pedidoFila.getNumeroNaFila())
                .idPedido(pedidoFila.getIdPedido())
                .build();
    }
}
