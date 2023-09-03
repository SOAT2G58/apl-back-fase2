package aplbackfase3.web.rest.produto.response;

import aplbackfase3.domain.entities.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProdutoResponse {
    private UUID id;
    private String nome;
    private String descricao;
    private String tipo;
    private BigDecimal valor;
}