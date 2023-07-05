package aplbackfase1.domain.model.valueObject;

import aplbackfase1.domain.exceptions.DescricaoProdutoInvalidoException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public final class DescricaoProduto {

    private String descricao;

    public DescricaoProduto(String descricaoProduto) {
        if(255 < descricaoProduto.length()) {
            throw new DescricaoProdutoInvalidoException();
        }
        this.descricao = descricaoProduto;
    }

}
