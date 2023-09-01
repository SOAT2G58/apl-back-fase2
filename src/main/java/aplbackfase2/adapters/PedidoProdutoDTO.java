package aplbackfase2.adapters;

import aplbackfase2.entities.PedidoProduto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoProdutoDTO {
    private UUID id;
    private UUID idProduto;
    private String descricaoProduto;
    private BigDecimal valorProduto;
    private String observacaoProduto;

    public static PedidoProdutoDTO from(PedidoProduto pedidoProduto) {
        return PedidoProdutoDTO.builder()
                .id(pedidoProduto.getId())
                .idProduto(pedidoProduto.getProdutoId())
                .descricaoProduto(pedidoProduto.getProdutoDescricao().getDescricao())
                .valorProduto(pedidoProduto.getValorProduto())
                .observacaoProduto(pedidoProduto.getObservacaoProduto())
                .build();
    }
}
