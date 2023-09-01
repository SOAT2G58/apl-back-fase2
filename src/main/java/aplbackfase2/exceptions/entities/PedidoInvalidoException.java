package aplbackfase2.exceptions.entities;

public class PedidoInvalidoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PedidoInvalidoException() {
        super("Pedido inválido");
    }
}
