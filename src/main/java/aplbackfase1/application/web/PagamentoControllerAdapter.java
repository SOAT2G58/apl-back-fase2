package aplbackfase1.application.web;

import aplbackfase1.application.web.responses.PagamentoDTO;
import aplbackfase1.domain.ports.in.IPagamentoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/tech-challenge")
@RequiredArgsConstructor
public class PagamentoControllerAdapter {

    private final IPagamentoUseCase pagamentoUseCase;

    @PostMapping("/pagamento/{idProduto}")
    public ResponseEntity<?> realizaPagamento(@PathVariable(name = "idProduto") UUID idProduto) {
        if (Objects.nonNull(idProduto)) {
            boolean res = pagamentoUseCase.realizarPagamento(idProduto);
            return res ? ResponseEntity.ok(new PagamentoDTO(true, "Pagamento realizado com sucesso!")) : ResponseEntity.internalServerError().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
