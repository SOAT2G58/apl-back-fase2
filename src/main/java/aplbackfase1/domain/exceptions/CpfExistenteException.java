package aplbackfase1.domain.exceptions;

public class CpfExistenteException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CpfExistenteException() {
        super("CPF já existente");
    }
}
