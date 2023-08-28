package aplbackfase1.infrastructure.persistence.entity;

import aplbackfase1.domain.enums.StatusPagamento;
import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.PedidoProduto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Calendar;
import java.util.stream.Collectors;

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
    @NotNull
    private UUID idCliente;

    @Enumerated(EnumType.STRING)
    @Column(name = "ID_STATUS")
    @NotNull
    private StatusPedido statusPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "ID_STATUS_PAGAMENTO")
    @NotNull
    private StatusPagamento statusPagamento;

    @Column(name = "V_PEDIDO")
    private BigDecimal valorPedido;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_H_INC")
    private Date dataInclusao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_H_ATC")
    private Date dataAtualizacao;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PedidoProdutoEntity> produtos;

    public Pedido to() {
        List<PedidoProduto> pedidoProdutos = null;
        if (this.produtos != null) {
            pedidoProdutos = this.produtos.stream()
                    .map(PedidoProdutoEntity::to)
                    .collect(Collectors.toList());
        }
        return Pedido.builder()
                .idPedido(this.idPedido)
                .idCliente(this.idCliente)
                .statusPedido(this.statusPedido)
                .statusPagamento(this.statusPagamento)
                .valorPedido(this.valorPedido)
                .produtos(pedidoProdutos)
                .dataInclusao(this.dataInclusao)
                .dataAtualizacao(this.dataAtualizacao)
                .build();
    }

    public PedidoEntity from(Pedido pedido, boolean isCreated) {
        PedidoEntityBuilder pedidoEntityBuilder = PedidoEntity.builder()
                .idPedido(pedido.getIdPedido())
                .idCliente(pedido.getIdCliente())
                .valorPedido(pedido.getValorPedido())
                .dataInclusao(pedido.getDataInclusao())
                .dataAtualizacao(pedido.getDataAtualizacao());

        if(isCreated) {
            pedidoEntityBuilder.dataInclusao(this.obterDataHoraAtual());
            pedidoEntityBuilder.statusPedido(StatusPedido.A);
            pedidoEntityBuilder.statusPagamento(StatusPagamento.PENDENTE);
        } else {
            pedidoEntityBuilder.statusPedido(pedido.getStatusPedido());
            pedidoEntityBuilder.statusPagamento(pedido.getStatusPagamento());
        }

        return pedidoEntityBuilder.build();
    }

    private Date obterDataHoraAtual(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE),
                calendar.get(Calendar.HOUR),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND));
        return new Date(calendar.getTime().getTime());
    }
}
