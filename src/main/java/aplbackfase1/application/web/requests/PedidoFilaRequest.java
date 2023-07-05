package aplbackfase1.application.web.requests;

import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.Produto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoFilaRequest {

    @NotNull
    private UUID idPedido;

    @NotNull
    private UUID idCliente;

    @NotNull
    private Date dataCriacao;

    private Date dataAtualizacao;

    private Date dataFinalizacao;

    @NotNull
    private StatusPedido status;

    @NotNull
    private List<ProdutoRequest> itens;

    public Pedido toPedido() {
        var produtos = getItens().stream().map(req -> {
            return req.from(req);
        }).collect(Collectors.toList());
        return Pedido.builder()
                .idPedido(this.getIdPedido())
                .idCliente(this.getIdCliente())
                .dataCriacao(this.getDataCriacao())
                .dataAtualizacao(this.getDataAtualizacao())
                .dataFinalizacao(this.getDataFinalizacao())
                .status(this.getStatus())
                .itens(produtos)
                .build();
    }
}
