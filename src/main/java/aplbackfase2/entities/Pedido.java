package aplbackfase2.entities;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import aplbackfase2.utils.enums.StatusPagamento;
import aplbackfase2.utils.enums.StatusPedido;

import java.util.Date;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

    private UUID idPedido;
    private UUID idCliente;
    private List<PedidoProduto> produtos;
    private StatusPedido statusPedido;
    private StatusPagamento statusPagamento;
    private BigDecimal valorPedido;
    private Date dataInclusao;
    private Date dataAtualizacao;

}
