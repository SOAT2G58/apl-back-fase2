package aplbackfase1.application.web.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PagamentoDTO {
    private boolean sucesso;
    private String menssagem;
}
