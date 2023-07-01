package aplbackfase1.domain.model;

import aplbackfase1.domain.enums.StatusPedido;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.Date;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido {

    private UUID id;
    private Cliente cliente;
    private List<PedidoProduto> produtos;
    private StatusPedido statusPedido;
    private BigDecimal valorPedido;
    private Date dataInclusao;
    private Date dataAtualizacao;

}
