package aplbackfase3.domain.exceptions;

public class PedidoProdutoNaoEncontradoException   extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PedidoProdutoNaoEncontradoException() {
        super("Produto não encontrado");
    }

    public PedidoProdutoNaoEncontradoException(String msg) {
        super(msg);
    }
}
