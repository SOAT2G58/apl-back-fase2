package aplbackfase2.usecases.requestValidations;

import aplbackfase2.utils.enums.StatusPedido;
import aplbackfase2.entities.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoFilaRequest {

    // TODO - Quando a feature de Pedido estiver pronta, ajustar para mapear aqui o objeto Pedido/PedidoDTO inteiro (que já contém odo o resto)
    @NotNull(message = "id do pedido não pode estar vazio")
    private UUID idPedido;

    @NotNull(message = "id do pedido não pode estar vazio")
    private UUID idCliente;


    private Date dataCriacao;

    private Date dataAtualizacao;

    private Date dataFinalizacao;

    @NotEmpty(message = "status do pedido não pode estar vazio")
    private StatusPedido status;

    @NotEmpty(message = "status do pedido não pode estar vazio")
    private List<ProdutoRequest> itens;

    public Pedido toPedido() {
        return Pedido.builder()
                .idPedido(this.getIdPedido())
                .idCliente(this.getIdCliente())
                .dataInclusao(this.getDataCriacao())
                .dataAtualizacao(this.getDataAtualizacao())
                .statusPedido(this.getStatus())
                .build();
    }
}
