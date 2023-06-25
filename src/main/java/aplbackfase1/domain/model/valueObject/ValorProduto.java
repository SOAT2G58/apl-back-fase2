package aplbackfase1.domain.model.valueObject;

import aplbackfase1.domain.model.exceptions.NomeProdutoInvalidoException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ValorProduto {
    private BigDecimal valorProduto;
}
