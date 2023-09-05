package aplbackfase2.entities;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class PedidoProduto {

    private UUID id;
    private UUID pedidoId;
    private UUID produtoId;
    private DescricaoProduto produtoDescricao;
    private BigDecimal valorProduto;
    private String observacaoProduto;

}
