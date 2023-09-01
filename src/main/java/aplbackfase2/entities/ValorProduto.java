package aplbackfase2.entities;

import aplbackfase2.exceptions.entities.ValorProdutoInvalidoException;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public final class ValorProduto {
    private BigDecimal valorProduto;

    public ValorProduto(BigDecimal valorProduto) {
        if (Objects.isNull(valorProduto)) {
            throw new ValorProdutoInvalidoException();
        }
        this.valorProduto = valorProduto.setScale(2);
    }
}
