package aplbackfase1.adapter.in.web.exceptions;

import aplbackfase1.adapter.in.web.ProdutoControllerAdapter;
import aplbackfase1.domain.exceptions.DescricaoProdutoInvalidoException;
import aplbackfase1.domain.exceptions.NomeProdutoInvalidoException;
import aplbackfase1.domain.exceptions.TipoProdutoInexistenteException;
import aplbackfase1.domain.exceptions.ValorProdutoInvalidoException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(assignableTypes = {ProdutoControllerAdapter.class})
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
}
