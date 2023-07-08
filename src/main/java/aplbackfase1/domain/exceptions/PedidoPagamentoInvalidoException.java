package aplbackfase1.domain.exceptions;

public class PedidoPagamentoInvalidoException  extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PedidoPagamentoInvalidoException() {
        super("Pagamento inválido para o pedido");
    }

    public PedidoPagamentoInvalidoException(String msg) {
        super(msg);
    }
}
