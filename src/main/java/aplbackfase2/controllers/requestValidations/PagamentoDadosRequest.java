package aplbackfase2.controllers.requestValidations;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PagamentoDadosRequest {
    //    private String id;
    private UUID idPedido;
}
