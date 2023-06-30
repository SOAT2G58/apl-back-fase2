package aplbackfase1.application.web.exceptions;

import aplbackfase1.application.web.ClienteControllerAdapter;
import aplbackfase1.domain.exceptions.CpfExistenteException;
import aplbackfase1.domain.exceptions.CpfInvalidoException;
import aplbackfase1.domain.exceptions.EmailInvalidoException;
import aplbackfase1.domain.exceptions.NomeInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(assignableTypes = {ClienteControllerAdapter.class})
public class ClienteExceptionHandler {
    @ExceptionHandler(CpfExistenteException.class)
    public ResponseEntity<StandardError> cpfExistente(CpfExistenteException e, HttpServletRequest request){
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "CPF já cadastrado", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(CpfInvalidoException.class)
    public ResponseEntity<StandardError> cpfInvalido(CpfInvalidoException e, HttpServletRequest request){
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "CPF inválido", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(EmailInvalidoException.class)
    public ResponseEntity<StandardError> emailInvalido(EmailInvalidoException e, HttpServletRequest request){
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Email inválido", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(NomeInvalidoException.class)
    public ResponseEntity<StandardError> nomeInvalido(NomeInvalidoException e, HttpServletRequest request){
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Nome inválido", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

}
