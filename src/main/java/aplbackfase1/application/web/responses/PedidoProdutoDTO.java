package aplbackfase1.application.web.responses;

import aplbackfase1.domain.model.PedidoProduto;
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
