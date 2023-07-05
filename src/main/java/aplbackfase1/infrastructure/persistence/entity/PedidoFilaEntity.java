package aplbackfase1.infrastructure.persistence.entity;

import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.PedidoFila;
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

//    @OneToMany
//    private Pedido pedido;

    private UUID idPedido;

    public PedidoFilaEntity(PedidoFila pedidoFila) {
        this.idPedido = pedidoFila.getPedido().getIdPedido();
    }

//    public PedidoFila toPedidoFila() {
//        return PedidoFila.builder()
//                .numeroNaFila(this.numeroNaFila)
//                .idPedido(this.idPedido)
//                .build();
//    }

}
