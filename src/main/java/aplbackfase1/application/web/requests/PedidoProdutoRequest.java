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

    private UUID idPedido;  // changed from id to idPedido
    private UUID idPedidoProduto;
    private BigDecimal valorProduto;
    private String observacaoProduto;

    public PedidoProduto from(PedidoProdutoRequest request) {
        return PedidoProduto.builder()
                .id(request.getIdPedido())  // changed from getId() to getIdPedido()
                .produtoId(request.getIdPedidoProduto())
                .valorProduto(request.getValorProduto())
                .observacaoProduto(request.getObservacaoProduto())
                .build();
    }
}
