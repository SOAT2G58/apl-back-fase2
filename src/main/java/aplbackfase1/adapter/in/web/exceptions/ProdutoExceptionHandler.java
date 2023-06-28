package aplbackfase1.adapter.in.web.exceptions;

import aplbackfase1.domain.model.exceptions.DescricaoProdutoInvalidoException;
import aplbackfase1.domain.model.exceptions.NomeProdutoInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ProdutoExceptionHandler {

    @ExceptionHandler(DescricaoProdutoInvalidoException.class)
    public ResponseEntity<StandardError> descricaoProdutoInvalido(DescricaoProdutoInvalidoException e, HttpServletRequest request){
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Descrição do produto inválida", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(NomeProdutoInvalidoException.class)
    public ResponseEntity<StandardError> nomeProdutoInvalidoException(NomeProdutoInvalidoException e, HttpServletRequest request){
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Nome do produto inválido", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

}
