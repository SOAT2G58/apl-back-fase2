package aplbackfase1.domain.model.valueObject;

import aplbackfase1.domain.exceptions.ValorProdutoInvalidoException;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ValorProduto {
    private BigDecimal valorProduto;

    public ValorProduto(BigDecimal valorProduto) {
        if (Objects.isNull(valorProduto)) {
            throw new ValorProdutoInvalidoException();
        }
        this.valorProduto = valorProduto.setScale(2);
    }
}
