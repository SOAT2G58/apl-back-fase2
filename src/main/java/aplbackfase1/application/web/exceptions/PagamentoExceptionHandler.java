package aplbackfase1.application.web.exceptions;

import aplbackfase1.application.web.PagamentoControllerAdapter;
import aplbackfase1.domain.exceptions.PedidoInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(assignableTypes = {PagamentoControllerAdapter.class})
public class PagamentoExceptionHandler {
    @ExceptionHandler(PedidoInvalidoException.class)
    public ResponseEntity<StandardError> pedidoInvalido(PedidoInvalidoException e, HttpServletRequest request){
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Pedido inválido", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
