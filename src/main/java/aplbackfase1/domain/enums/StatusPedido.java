package aplbackfase1.domain.enums;

public enum StatusPedido {
    A("Aberto"),
    C("Concluído"),
    E("Em preparação"),
    R("A retirar"),
    F("Finalizado");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
