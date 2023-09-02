package aplbackfase3.domain.exceptions;

public class ValorProdutoInvalidoException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ValorProdutoInvalidoException() {
        super("Valor do produto não pode ser nulo");
    }
}
