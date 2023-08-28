package aplbackfase1.application.web;

import aplbackfase1.application.web.requests.PagamentoNotificacaoRequest;
import aplbackfase1.application.web.responses.PagamentoDTO;
import aplbackfase1.domain.enums.StatusPagamento;
import aplbackfase1.domain.ports.in.IPagamentoUseCase;
import aplbackfase1.domain.ports.in.IPedidoUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/pagamento/webhook")
    public ResponseEntity<?> localizarPagamentoDoPedido(@RequestBody @Valid PagamentoNotificacaoRequest pagamentoNotificacaoRequest) {
        if (Objects.isNull(pagamentoNotificacaoRequest.getPagamentoDados()) || Objects.isNull(pagamentoNotificacaoRequest.getPagamentoDados().getIdPedido())) {
            return ResponseEntity.badRequest().build();
        }
        if (Objects.isNull(pagamentoNotificacaoRequest.getAcao()) || pagamentoNotificacaoRequest.getAcao().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        if (pagamentoNotificacaoRequest.getAcao().toLowerCase().equals("pagamento.aprovado")) {
            pedidoUseCasePort.atualizarStatusPagamento(StatusPagamento.APROVADO, pagamentoNotificacaoRequest.getPagamentoDados().getIdPedido());
        }

        if (pagamentoNotificacaoRequest.getAcao().toLowerCase().equals("pagamento.recusado")) {
            pedidoUseCasePort.atualizarStatusPagamento(StatusPagamento.RECUSADO, pagamentoNotificacaoRequest.getPagamentoDados().getIdPedido());
        }

        return ResponseEntity.ok().build();
    }
}
