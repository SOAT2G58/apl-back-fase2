package aplbackfase1.application.web.requests;

import aplbackfase1.domain.model.Cliente;
import aplbackfase1.domain.model.Pedido;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {

    @NotNull
    private Cliente idCliente;

    public Pedido from(PedidoRequest request) {
        return Pedido.builder()
                .cliente(request.idCliente)
                .build();
    }
}
