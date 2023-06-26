package aplbackfase1.domain.model.valueObject;

import aplbackfase1.domain.model.exceptions.NomeProdutoInvalidoException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class NomeProduto {

    private String nome;

    public NomeProduto(String nomeProduto) {
        if (nomeProduto == null || nomeProduto.trim().length() < 4 || nomeProduto.trim().length() > 20) {
            throw new NomeProdutoInvalidoException();
        }
        this.nome = nomeProduto.trim();
    }
}
