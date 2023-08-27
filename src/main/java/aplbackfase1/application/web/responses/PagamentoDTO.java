package aplbackfase1.application.web.responses;

import aplbackfase1.domain.enums.StatusPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PagamentoDTO {
    private StatusPagamento status;
    private String menssagem;

    public PagamentoDTO (StatusPagamento status) {
        this.status = status;
        this.menssagem = status.getDescricao();
    }
}
