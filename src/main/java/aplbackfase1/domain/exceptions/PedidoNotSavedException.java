package aplbackfase1.domain.exceptions;

public class PedidoNotSavedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PedidoNotSavedException() {
        super("Pedido sem ID. O pedido foi salvo antes de enviar para a fila de preparo ?");
    }

}
