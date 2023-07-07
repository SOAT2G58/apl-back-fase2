package aplbackfase1.application.web.requests;

import aplbackfase1.domain.model.PedidoProduto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProdutoRequest {

    private UUID id;
    private UUID idPedido;
    private UUID idProduto;
    private BigDecimal valorProduto;
    private String observacaoProduto;

    public PedidoProduto from(PedidoProdutoRequest request) {
        return PedidoProduto.builder()
                .id(request.getId())
                .pedidoId(request.getIdPedido())
                .produtoId(request.getIdProduto())
                .valorProduto(request.getValorProduto())
                .observacaoProduto(request.getObservacaoProduto())
                .build();
    }
}
