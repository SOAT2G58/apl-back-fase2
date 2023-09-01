package aplbackfase2.exceptions.entities;

public class PedidoNaoEncontradoNaFilaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PedidoNaoEncontradoNaFilaException() {
        super("Não foi encontrado um pedido com esse número na fila");
    }
}
