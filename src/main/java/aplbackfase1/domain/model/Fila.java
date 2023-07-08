package aplbackfase1.domain.model;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fila {

    private UUID idFila;
    private UUID idPedido;
    private int numeroNaFila;
    private Date dataInclusaoFila;

}
