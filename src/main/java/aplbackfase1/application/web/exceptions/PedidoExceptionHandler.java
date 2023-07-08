package aplbackfase1.application.web.exceptions;

import aplbackfase1.application.web.PedidoControllerAdapter;
import aplbackfase1.domain.exceptions.PedidoNaoEncontradoException;
import aplbackfase1.domain.exceptions.PedidoOperacaoNaoSuportadaException;
import aplbackfase1.domain.exceptions.PedidoPagamentoInvalidoException;
import aplbackfase1.domain.exceptions.PedidoProdutoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(assignableTypes = {PedidoControllerAdapter.class})
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
