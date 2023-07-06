package aplbackfase1.domain.exceptions;

public class PedidoInvalidoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PedidoInvalidoException() {
        super("Pedido inválido");
    }
}
