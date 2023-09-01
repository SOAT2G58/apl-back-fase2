package aplbackfase1.infrastructure.persistence.entity;

import aplbackfase2.entities.PedidoProduto;
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
@Table(name = "PEDIDO_PRODUTOS")
public class PedidoProdutoEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID_PED_PROD")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ID_PEDIDO")
    private PedidoEntity pedido;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO")
    private ProdutoEntity produto;

    @Column(name = "V_PRODUTO")
    private BigDecimal valorProduto;

    @Column(name = "TXT_OBS_PRODUTO")
    private String observacaoProduto;

    public static PedidoProduto to(PedidoProdutoEntity pedidoProdutoEntity) {
        return PedidoProduto.builder()
                .id(pedidoProdutoEntity.getId())
                .pedidoId(pedidoProdutoEntity.getPedido().getIdPedido())
                .produtoId(pedidoProdutoEntity.getProduto().getIdProduto())
                .produtoDescricao(pedidoProdutoEntity.getProduto().getDescricaoProduto())
                .valorProduto(pedidoProdutoEntity.getValorProduto())
                .observacaoProduto(pedidoProdutoEntity.getObservacaoProduto())
                .build();
    }

    public static PedidoProdutoEntity from(PedidoProduto pedidoProduto, PedidoEntity pedidoEntity, ProdutoEntity produtoEntity) {
        return PedidoProdutoEntity.builder()
                .id(pedidoProduto.getId())
                .pedido(pedidoEntity)
                .produto(produtoEntity)
                .valorProduto(pedidoProduto.getValorProduto())
                .observacaoProduto(pedidoProduto.getObservacaoProduto())
                .build();
    }
}
