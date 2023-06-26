package aplbackfase1.domain.model.valueObject;

import aplbackfase1.domain.model.exceptions.NomeProdutoInvalidoException;
import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ValorProduto {
    private BigDecimal valorProduto;
}
