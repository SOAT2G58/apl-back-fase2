package aplbackfase1.domain.exceptions;

public class PedidoNaoEncontradoNaFilaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PedidoNaoEncontradoNaFilaException() {
        super("Não foi encontrado um pedido com esse número na fila");
    }
}
