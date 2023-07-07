package aplbackfase1.infrastructure.persistence.entity;

import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID_PEDIDO")
    private UUID idPedido;

    @Column(name = "ID_CLIENTE")
    private UUID idCliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "ID_STATUS")
    private StatusPedido statusPedido;

    @Column(name = "V_PEDIDO")
    private BigDecimal valorPedido;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_H_INC")
    private Date dataInclusao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_H_ATC")
    private Date dataAtualizacao;

    public Pedido to() {
        return Pedido.builder()
                .idPedido(this.idPedido)
                .idCliente(this.idCliente)
                .statusPedido(this.statusPedido)
                .valorPedido(this.valorPedido)
                .dataInclusao(this.dataInclusao)
                .dataAtualizacao(this.dataAtualizacao)
                .build();
    }

    public PedidoEntity from(Pedido pedido) {
        return PedidoEntity.builder()
                .idPedido(pedido.getIdPedido())
                .idCliente(pedido.getIdCliente())
                .statusPedido(pedido.getStatusPedido())
                .valorPedido(pedido.getValorPedido())
                .dataInclusao(pedido.getDataInclusao())
                .dataAtualizacao(pedido.getDataAtualizacao())
                .build();
    }
}
