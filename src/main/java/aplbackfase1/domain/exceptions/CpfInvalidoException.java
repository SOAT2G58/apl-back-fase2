package aplbackfase1.domain.exceptions;

public class CpfInvalidoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CpfInvalidoException() {
        super("CPF inválido");
    }
}
