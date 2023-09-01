package aplbackfase2.exceptions.entities;

import java.util.UUID;

public class PedidoJaNaFilaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PedidoJaNaFilaException(UUID idPedido) {
        super("Pedido com ID: " + idPedido + " já está na fila.");
    }
}
