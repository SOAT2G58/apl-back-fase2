package aplbackfase1.application.web;

import aplbackfase1.application.web.responses.PagamentoDTO;
import aplbackfase1.domain.ports.in.IPagamentoUseCase;
import aplbackfase1.domain.ports.in.IPedidoUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/tech-challenge")
@RequiredArgsConstructor
public class PagamentoControllerAdapter {

    private final IPagamentoUseCase pagamentoUseCase;
    private final IPedidoUseCasePort pedidoUseCasePort;

    @PostMapping("/pagamento/{idPedido}")
    public ResponseEntity<?> realizaPagamento(@PathVariable(name = "idPedido") UUID idPedido) {
        if (Objects.nonNull(idPedido)) {
            boolean res = pagamentoUseCase.realizarPagamento(idPedido);
            // TODO - Colocar o pedido na fila com status RECEBIDO
            return res ? ResponseEntity.ok("Pagamento realizado com sucesso!") : ResponseEntity.internalServerError().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/pagamento/{idPedido}")
    public ResponseEntity<?> localizarPagamentoDoPedido(@PathVariable(name = "idPedido") UUID idPedido) {
        if (Objects.nonNull(idPedido)) {
            return ResponseEntity.ok(new PagamentoDTO(pagamentoUseCase.localizarStatusPagamento(idPedido, pedidoUseCasePort)));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
