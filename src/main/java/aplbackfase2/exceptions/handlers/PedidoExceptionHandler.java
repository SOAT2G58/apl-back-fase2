package aplbackfase2.exceptions.handlers;

import aplbackfase2.controllers.PedidoController;
import aplbackfase2.exceptions.entities.PedidoNaoEncontradoException;
import aplbackfase2.exceptions.entities.PedidoOperacaoNaoSuportadaException;
import aplbackfase2.exceptions.entities.PedidoPagamentoInvalidoException;
import aplbackfase2.exceptions.entities.PedidoProdutoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(assignableTypes = {PedidoController.class})
public class PedidoExceptionHandler {
    @ExceptionHandler(PedidoNaoEncontradoException.class)
    public ResponseEntity<StandardError> pedidoNaoEncontrado(PedidoNaoEncontradoException e, HttpServletRequest request){
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Pedido não encontrado", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(PedidoOperacaoNaoSuportadaException.class)
    public ResponseEntity<StandardError> pedidoOperacaoNaoSuportada(PedidoOperacaoNaoSuportadaException e, HttpServletRequest request){
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Operação não suportada, verifique o status do pedido", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(PedidoPagamentoInvalidoException.class)
    public ResponseEntity<StandardError> pedidoPagamentoInvalido(PedidoPagamentoInvalidoException e, HttpServletRequest request){
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Pagamento inválido para o pedido", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(PedidoProdutoNaoEncontradoException.class)
    public ResponseEntity<StandardError> pedidoProdutoNaoEncontrado(PedidoProdutoNaoEncontradoException e, HttpServletRequest request){
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Produto não encontrado", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
