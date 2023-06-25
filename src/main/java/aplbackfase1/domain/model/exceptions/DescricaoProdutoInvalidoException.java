package aplbackfase1.domain.model.exceptions;

public class DescricaoProdutoInvalidaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DescricaoProdutoInvalidaException() {
        super("Descriçao do produto muito grande, tamanho maximo permitido: 255 caracteres");
    }
}