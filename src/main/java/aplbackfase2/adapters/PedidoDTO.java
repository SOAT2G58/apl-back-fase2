package aplbackfase2.adapters;

import aplbackfase2.utils.enums.StatusPagamento;
import aplbackfase2.utils.enums.StatusPedido;
import aplbackfase2.entities.Pedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PedidoDTO {
    private UUID idPedido;
    private UUID idCliente;
    private List<PedidoProdutoDTO> pedidoProdutos;
    private StatusPedido statusPedido;
    private StatusPagamento statusPagamento;
    private BigDecimal valorPedido;
    private Date dataInclusao;
    private Date dataAtualizacao;

    public static PedidoDTO from(Pedido pedido) {
        List<PedidoProdutoDTO> pedidoProdutoDTO = null;
        if (pedido.getProdutos() != null) {
            pedidoProdutoDTO = pedido.getProdutos().stream()
                    .map(PedidoProdutoDTO::from)
                    .collect(Collectors.toList());
        }
        return PedidoDTO.builder()
                .idPedido(pedido.getIdPedido())
                .idCliente(pedido.getIdCliente())
                .pedidoProdutos(pedidoProdutoDTO)
                .statusPedido(pedido.getStatusPedido())
                .statusPagamento(pedido.getStatusPagamento())
                .valorPedido(pedido.getValorPedido())
                .dataInclusao(pedido.getDataInclusao())
                .dataAtualizacao(pedido.getDataAtualizacao())
                .build();
    }
}
