package aplbackfase2.exceptions.entities;

public class NomeProdutoInvalidoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NomeProdutoInvalidoException() {
        super("Nome do produto deve ter entre 4 e 20 caracteres");
    }
}