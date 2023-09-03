package aplbackfase3.domain.usecases.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ProdutoDAO {
    private String nome;
    private String descricao;
    private String tipo;
    private BigDecimal valor;

    private UUID id;
}