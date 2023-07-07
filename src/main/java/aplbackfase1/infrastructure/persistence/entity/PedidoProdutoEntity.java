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
@Table(name = "PEDIDO_PRODUTOS")
public class PedidoProdutoEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID_PED_PROD")
    private UUID id;

    @Column(name = "ID_PEDIDO")
    private UUID idPedido;

    @Column(name = "ID_PRODUTO")
    private UUID idProduto;

    @Column(name = "V_PRODUTO")
    private BigDecimal valorProduto;

    @Column(name = "TXT_OBS_PRODUTO")
    private String observacaoProduto;

    public static PedidoProduto to(PedidoProdutoEntity pedidoProdutoEntity) {
        return PedidoProduto.builder()
                .id(pedidoProdutoEntity.getId())
                .pedidoId(pedidoProdutoEntity.getIdPedido())
                .produtoId(pedidoProdutoEntity.getIdProduto())
                .valorProduto(pedidoProdutoEntity.getValorProduto())
                .observacaoProduto(pedidoProdutoEntity.getObservacaoProduto())
                .build();
    }

    public static PedidoProdutoEntity from(PedidoProduto pedidoProduto) {
        return PedidoProdutoEntity.builder()
                .id(pedidoProduto.getId())
                .idPedido(pedidoProduto.getPedidoId())
                .idProduto(pedidoProduto.getProdutoId())
                .valorProduto(pedidoProduto.getValorProduto())
                .observacaoProduto(pedidoProduto.getObservacaoProduto())
                .build();
    }
}
