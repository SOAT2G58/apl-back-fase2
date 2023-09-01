package aplbackfase2.exceptions.entities;

public class DescricaoProdutoInvalidoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DescricaoProdutoInvalidoException() {
        super("Descriçao do produto muito grande, tamanho maximo permitido: 255 caracteres");
    }
}