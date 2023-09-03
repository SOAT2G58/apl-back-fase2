package aplbackfase3.domain.entities;

import aplbackfase3.domain.entities.enums.TipoProduto;
import aplbackfase3.domain.usecases.dao.ProdutoDAO;
import aplbackfase3.domain.valueObjects.DescricaoProduto;
import aplbackfase3.domain.valueObjects.NomeProduto;
import aplbackfase3.domain.valueObjects.ValorProduto;
import lombok.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public final class Produto {
    private UUID idProduto;
    private NomeProduto nomeProduto;
    private DescricaoProduto descricaoProduto;
    private TipoProduto tipoProduto;
    private ValorProduto valorProduto;
}
