package aplbackfase2.exceptions.entities;

public class PedidoOperacaoNaoSuportadaException  extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PedidoOperacaoNaoSuportadaException() {
        super("Operação não suportada");
    }

    public PedidoOperacaoNaoSuportadaException(String msg) {
        super(msg);
    }
}
