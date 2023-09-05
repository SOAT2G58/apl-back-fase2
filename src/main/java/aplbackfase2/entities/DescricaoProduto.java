package aplbackfase2.entities;

import aplbackfase2.exceptions.entities.DescricaoProdutoInvalidoException;
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
