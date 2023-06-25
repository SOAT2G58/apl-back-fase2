package aplbackfase1.domain.model;

import aplbackfase1.domain.model.valueObject.DescricaoProduto;
import aplbackfase1.domain.model.valueObject.NomeProduto;
import aplbackfase1.domain.model.valueObject.ValorProduto;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode
@Data
@Builder
@Entity
public class Produto {
    @Id
    private UUID idProduto;
    private NomeProduto nomeProduto;
    private DescricaoProduto descricaoProduto;
    private TipoProduto tipoProduto;

    private ValorProduto valorProduto;

}
