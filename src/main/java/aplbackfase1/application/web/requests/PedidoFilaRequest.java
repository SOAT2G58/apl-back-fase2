package aplbackfase1.application.web.requests;

import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoFilaRequest {

    // TODO - Quando a feature de Pedido estiver pronta, ajustar para mapear aqui o objeto Pedido/PedidoDTO inteiro (que já contém odo o resto)
    @NotEmpty(message = "id do pedido não pode estar vazio")
    private UUID idPedido;

    @NotEmpty(message = "id do pedido não pode estar vazio")
    private UUID idCliente;


    private Date dataCriacao;

    private Date dataAtualizacao;

    private Date dataFinalizacao;

    @NotEmpty(message = "status do pedido não pode estar vazio")
    private StatusPedido status;

    @NotEmpty(message = "status do pedido não pode estar vazio")
    private List<ProdutoRequest> itens;

    public Pedido toPedido() {
        var produtos = getItens().stream().map(req -> {
            return req.from(req);
        }).collect(Collectors.toList());
        return Pedido.builder()
                .idPedido(this.getIdPedido())
                .idCliente(this.getIdCliente())
                .dataInclusao(this.getDataCriacao())
                .dataAtualizacao(this.getDataAtualizacao())
                // .dataFinalizacao(this.getDataFinalizacao())
                .statusPedido(this.getStatus())
                // .itens(produtos)
                .build();
    }
}
