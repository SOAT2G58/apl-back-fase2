package aplbackfase2.utils.enums;

import aplbackfase2.exceptions.entities.TipoProdutoInexistenteException;

import java.util.Objects;

public enum TipoProduto {
    LANCHE("1"),
    BEBIDA("2"),
    SOBREMESA("3"),
    ACOMPANHAMENTO("4");

    private String codigo;

    TipoProduto(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public static TipoProduto fromCodigo(String codigo) {
        if(Objects.nonNull(codigo)) {
            for (TipoProduto tipo : TipoProduto.values()) {
                if (codigo.equals(tipo.getCodigo())) return tipo;
            }
        }
        throw new TipoProdutoInexistenteException(codigo);
    }
}
