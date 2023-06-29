package aplbackfase1.domain.enums;

import aplbackfase1.domain.exceptions.TipoProdutoInexistenteException;

import java.util.Objects;

public enum TipoProduto {
    LANCHE("L"),
    BEBIDA("B"),
    SOBREMESA("S"),
    ACOMPANHAMENTO("A");

    private String codigo;

    TipoProduto(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public static TipoProduto fromCodigo(String codigo) {
        for (TipoProduto tipo : TipoProduto.values()) {
            if (Objects.nonNull(codigo) && tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new TipoProdutoInexistenteException(codigo);
    }
}
