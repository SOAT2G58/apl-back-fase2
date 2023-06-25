package aplbackfase1.domain.model.valueObject;

import aplbackfase1.domain.model.exceptions.NomeProdutoInvalidoException;

public class NomeProduto {

    private String nome;

    public NomeProduto(String nomeProduto) {
        if (nomeProduto == null || nomeProduto.trim().length() < 4 || nomeProduto.trim().length() > 20) {
            throw new NomeProdutoInvalidoException();
        }
        this.nome = nomeProduto.trim();
    }

    public String getNome() {
        return this.nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NomeProduto that = (NomeProduto) obj;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    @Override
    public String toString() {
        return nome;
    }
}
