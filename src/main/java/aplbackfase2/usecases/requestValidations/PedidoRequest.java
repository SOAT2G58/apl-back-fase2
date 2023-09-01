package aplbackfase2.usecases.requestValidations;

import aplbackfase2.entities.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {

    @NotNull(message = "id cliente não pode estar vazio")
    private UUID idCliente;

    public Pedido from(PedidoRequest request) {

        return Pedido.builder()
                .idCliente(request.getIdCliente())
                .build();
    }
}
