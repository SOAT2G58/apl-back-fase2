package aplbackfase1.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoProduto {

    private UUID idPedido;
    private UUID idProduto;
    private BigDecimal valorProduto;
    private String observacaoProduto;

}
