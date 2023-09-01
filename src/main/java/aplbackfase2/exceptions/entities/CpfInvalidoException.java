package aplbackfase2.exceptions.entities;

public class CpfInvalidoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CpfInvalidoException() {
        super("CPF inválido");
    }
}
