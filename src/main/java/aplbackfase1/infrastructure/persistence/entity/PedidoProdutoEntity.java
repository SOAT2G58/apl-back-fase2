package aplbackfase1.infrastructure.persistence.entity;

import aplbackfase1.domain.model.PedidoProduto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pedido_produtos")
public class PedidoProdutoEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID_PED_PROD")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;

    @Column(name = "V_PRODUTO")
    private BigDecimal valorProduto;

    @Column(name = "TXT_OBS_PRODUTO")
    private String observacaoProduto;

    public static PedidoProduto to(PedidoProdutoEntity pedidoProdutoEntity) {
        return PedidoProduto.builder()
                .idPedido(pedidoProdutoEntity.getPedido().getIdPedido())
                .idProduto(pedidoProdutoEntity.getProduto().getIdProduto())
                .valorProduto(pedidoProdutoEntity.getValorProduto())
                .observacaoProduto(pedidoProdutoEntity.getObservacaoProduto())
                .build();
    }

    public PedidoProdutoEntity from(PedidoProduto pedidoProduto,
                                    PedidoEntity pedidoEntity,
                                    ProdutoEntity produtoEntity) {
        return PedidoProdutoEntity.builder()
                .pedido(pedidoEntity)
                .produto(produtoEntity)
                .valorProduto(pedidoProduto.getValorProduto())
                .observacaoProduto(pedidoProduto.getObservacaoProduto())
                .build();
    }
}
