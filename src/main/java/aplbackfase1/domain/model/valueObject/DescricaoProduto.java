package aplbackfase1.domain.model.valueObject;

import aplbackfase1.domain.model.exceptions.DescricaoProdutoInvalidoException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Converter;
import javax.persistence.Embeddable;

@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class DescricaoProduto {

    private String descricao;

    public DescricaoProduto(String descricaoProduto) {
        if(descricaoProduto.length() > 255) {
            throw new DescricaoProdutoInvalidoException();
        }
        this.descricao = descricaoProduto;
    }

}
