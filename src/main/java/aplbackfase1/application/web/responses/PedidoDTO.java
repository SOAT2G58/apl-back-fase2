package aplbackfase1.application.web.responses;

import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.domain.model.PedidoProduto;
import aplbackfase1.domain.model.Pedido;
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
    private ClienteDTO cliente;
    private List<PedidoProdutoDTO> produtos;
    private StatusPedido statusPedido;
    private BigDecimal valorPedido;
    private Date dataInclusao;
    private Date dataAtualizacao;

    public PedidoDTO from(Pedido pedido) {
        return PedidoDTO.builder()
                .idPedido(pedido.getIdPedido())
                .cliente(new ClienteDTO().from(pedido.getCliente()))
                .produtos(pedido.getProdutos().stream().map(PedidoProdutoDTO::from).collect(Collectors.toList()))
                .statusPedido(pedido.getStatusPedido())
                .valorPedido(pedido.getValorPedido())
                .dataInclusao(pedido.getDataInclusao())
                .dataAtualizacao(pedido.getDataAtualizacao())
                .build();
    }
}
