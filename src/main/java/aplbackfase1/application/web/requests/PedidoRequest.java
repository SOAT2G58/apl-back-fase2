package aplbackfase1.application.web.requests;

import aplbackfase1.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {

    @NotEmpty(message = "id cliente não pode estar vazio")
    private UUID idCliente;

    public Pedido from(PedidoRequest request) {

        return Pedido.builder()
                .idCliente(request.getIdCliente())
                .build();
    }
}
