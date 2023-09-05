package aplbackfase2.exceptions.entities;

public class PedidoNotSavedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PedidoNotSavedException() {
        super("Pedido sem ID. O pedido foi salvo antes de enviar para a fila de preparo ?");
    }

}
