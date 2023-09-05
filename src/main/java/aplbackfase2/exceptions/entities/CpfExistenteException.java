package aplbackfase2.exceptions.entities;

public class CpfExistenteException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CpfExistenteException() {
        super("CPF já existente");
    }
}
