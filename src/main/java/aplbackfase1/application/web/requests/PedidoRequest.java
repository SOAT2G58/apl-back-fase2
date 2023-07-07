package aplbackfase1.application.web.requests;

import aplbackfase1.domain.model.Cliente;
import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.PedidoProduto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {

    @NotNull
    private UUID idCliente;
    private List<PedidoProdutoRequest> produtos;

    public Pedido from(PedidoRequest request) {
        List<PedidoProduto> pedidoProdutos = request.getProdutos().stream()
                .map(ppRequest -> ppRequest.from(ppRequest))
                .collect(Collectors.toList());

        return Pedido.builder()
                .idCliente(request.getIdCliente())
                .produtos(pedidoProdutos)
                .build();
    }
}
