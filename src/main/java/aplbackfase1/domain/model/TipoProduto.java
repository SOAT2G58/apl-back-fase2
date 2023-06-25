package aplbackfase1.domain.model;

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
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Invalid TipoProduto codigo: " + codigo);
    }
}
