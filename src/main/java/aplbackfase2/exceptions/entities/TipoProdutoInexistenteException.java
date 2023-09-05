package aplbackfase2.exceptions.entities;

public class TipoProdutoInexistenteException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TipoProdutoInexistenteException(String tipo) {
        super("Tipo produto ".concat(tipo).concat(" não existe"));
    }
}
