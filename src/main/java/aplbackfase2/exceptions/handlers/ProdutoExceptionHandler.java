package aplbackfase2.exceptions.handlers;

import aplbackfase2.controllers.ProdutoController;
import aplbackfase2.exceptions.entities.DescricaoProdutoInvalidoException;
import aplbackfase2.exceptions.entities.NomeProdutoInvalidoException;
import aplbackfase2.exceptions.entities.TipoProdutoInexistenteException;
import aplbackfase2.exceptions.entities.ValorProdutoInvalidoException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(assignableTypes = {ProdutoController.class})
public class ProdutoExceptionHandler {

    @ExceptionHandler(DescricaoProdutoInvalidoException.class)
    public ResponseEntity<StandardError> descricaoProdutoInvalido(DescricaoProdutoInvalidoException e, HttpServletRequest request){
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Descrição do produto inválida", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(NomeProdutoInvalidoException.class)
    public ResponseEntity<StandardError> nomeProdutoInvalidoException(
            NomeProdutoInvalidoException e, HttpServletRequest request){
        StandardError err = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Nome do produto inválido",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(TipoProdutoInexistenteException.class)
    public ResponseEntity<StandardError> tipoProdutoInexistenteException(
            TipoProdutoInexistenteException e, HttpServletRequest request){
        StandardError err = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Tipo do produto está nulo, vazio ou não existe",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ValorProdutoInvalidoException.class)
    public ResponseEntity<StandardError> valorProdutoInvalidoException(
            ValorProdutoInvalidoException e, HttpServletRequest request){
        StandardError err = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "valor do produto não pode ser nulo",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<StandardError> emptyResultDataAccessException(
            EmptyResultDataAccessException e, HttpServletRequest request){
        StandardError err = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                "A busca ao banco retornou status vazio para o elemento informado",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> emptyResultDataAccessException(
            MethodArgumentNotValidException e, HttpServletRequest request){

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) ->{

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        StandardError err = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                errors.toString(),
                "",
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
