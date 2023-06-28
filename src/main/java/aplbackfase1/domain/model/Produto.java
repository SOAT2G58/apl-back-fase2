package aplbackfase1.domain.model;

import aplbackfase1.domain.model.valueObject.DescricaoProduto;
import aplbackfase1.domain.model.valueObject.NomeProduto;
import aplbackfase1.domain.model.valueObject.ValorProduto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {
    private UUID idProduto;
    private NomeProduto nomeProduto;
    private DescricaoProduto descricaoProduto;
    private TipoProduto tipoProduto;
    private ValorProduto valorProduto;
    private Date dataAtualizacao;
    private Date dataCriacao;
}
