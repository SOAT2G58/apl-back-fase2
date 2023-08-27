package aplbackfase1.domain.enums;

public enum StatusPagamento {
    PENDENTE("Pagamento pendente"),
    APROVADO("Pagamento aprovado"),
    RECUSADO("Pagamento recusado");

    private final String descricao;

    StatusPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
