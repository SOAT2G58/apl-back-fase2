package aplbackfase1.application.web;

import aplbackfase1.application.web.requests.PedidoFilaRequest;
import aplbackfase1.application.web.responses.PedidoFilaDTO;
import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.ports.in.IFilaUseCasePort;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tech-challenge")
@RequiredArgsConstructor
public class FilaControllerAdapter {

    @Autowired
    private IFilaUseCasePort filaUseCasePort;

    @PostMapping("/fila")
    public ResponseEntity<?> inserePedidoNaFila(@RequestBody @NotNull PedidoFilaRequest pedidoFilaRequest) {
        // TODO - passar o pedidoDTO (quando tiver) junto com a responsta

        try {
            var pedidoFila = filaUseCasePort.inserirPedidoNaFila(pedidoFilaRequest.toPedido());
            var pedidoFilaDTO = new PedidoFilaDTO().from(pedidoFila);
            return ResponseEntity.ok().body(pedidoFilaDTO);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

    }

    @GetMapping("/fila/{numero}")
    public ResponseEntity<PedidoFilaDTO> buscarPedidoNaFilaPorNumero(@PathVariable Long numero) {
        // TODO - passar o pedidoDTO (quando tiver) junto com a responsta

        var pedidoFila = filaUseCasePort.obterPedidoPorNumeroNaFila(numero);
        if (pedidoFila.isPresent()) {
            var pedidoFilaDTO = new PedidoFilaDTO().from(pedidoFila.get());
            return ResponseEntity.ok().body(pedidoFilaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fila")
    public ResponseEntity<PedidoFilaDTO> buscarPedidoNaFilaPorID(@RequestParam(name = "id") UUID idPedido) {
        // TODO - passar o pedidoDTO (quando tiver) junto com a responsta

        var pedidoFila = filaUseCasePort.obterPedidoPorIdPedido(idPedido);
        if (pedidoFila.isPresent()) {
            var pedidoFilaDTO = new PedidoFilaDTO().from(pedidoFila.get());
            return ResponseEntity.ok().body(pedidoFilaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
