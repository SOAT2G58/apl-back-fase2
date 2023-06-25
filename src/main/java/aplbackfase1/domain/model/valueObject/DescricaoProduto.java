package aplbackfase1.domain.model.valueObject;

import aplbackfase1.domain.model.exceptions.DescricaoProdutoInvalidaException;

public class DescricaoProduto {

    private final String descricao;

    public DescricaoProduto(String descricaoProduto) {
        if(descricaoProduto.length() > 255) {
            throw new DescricaoProdutoInvalidaException();
        }
        this.descricao = descricaoProduto;
    }

    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DescricaoProduto that = (DescricaoProduto) obj;
        return descricao.equals(that.descricao);
    }

    @Override
    public int hashCode() {
        return descricao.hashCode();
    }

    @Override
    public String toString() {
        return descricao;
    }
}
