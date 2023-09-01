package aplbackfase1.infrastructure.persistence.entity;

import aplbackfase2.adapters.PedidoFilaDTO;
import aplbackfase2.entities.PedidoFila;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "fila")
public class PedidoFilaEntity {

    @Id
    @GeneratedValue
    private Long numeroNaFila;

    private UUID idPedido;

    public PedidoFilaEntity(PedidoFila pedidoFila) {
        this.idPedido = pedidoFila.getIdPedido();
        this.numeroNaFila = pedidoFila.getNumeroNaFila();
    }

    public PedidoFila toPedidoFila() {
        var pedidoFila = new PedidoFila();
        pedidoFila.setNumeroNaFila(this.numeroNaFila);
        pedidoFila.setIdPedido(this.idPedido);
        return pedidoFila;
    }
}
