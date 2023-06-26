package aplbackfase1.domain.model.valueObject;

import aplbackfase1.domain.model.exceptions.DescricaoProdutoInvalidoException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class DescricaoProduto {

    private final String descricao;

    public DescricaoProduto(String descricaoProduto) {
        if(descricaoProduto.length() > 255) {
            throw new DescricaoProdutoInvalidoException();
        }
        this.descricao = descricaoProduto;
    }

}
