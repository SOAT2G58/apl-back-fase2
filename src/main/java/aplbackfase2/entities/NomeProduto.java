package aplbackfase2.entities;

import aplbackfase2.exceptions.entities.NomeProdutoInvalidoException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public final class NomeProduto {

    private String nome;

    public NomeProduto(String nomeProduto) {
        if (Objects.isNull(nomeProduto) || 4 > nomeProduto.trim().length() || 20 < nomeProduto.trim().length()) {
            throw new NomeProdutoInvalidoException();
        }
        this.nome = nomeProduto.trim();
    }
}
