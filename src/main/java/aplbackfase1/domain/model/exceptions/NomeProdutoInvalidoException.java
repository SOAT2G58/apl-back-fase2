package aplbackfase1.domain.model.exceptions;

public class NomeProdutoInvalidoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NomeProdutoInvalidoException() {
        super("Nome do produto deve ter entre 4 e 20 caracteres");
    }
}