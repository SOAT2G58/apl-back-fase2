package aplbackfase2.controllers;

import aplbackfase2.adapters.PedidoFilaDTO;
import aplbackfase2.interfaces.usecases.IFilaUseCasePort;
import aplbackfase2.usecases.requestValidations.PedidoFilaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @GetMapping("/fila/pedidos")
    public Page<PedidoFilaDTO> buscarPedidosNaFila(
            @PageableDefault(size = 10, page = 0) @SortDefault(sort = "numeroNaFila",
            direction = Sort.Direction.ASC) Pageable paginacao) {
        // TODO - passar o pedidoDTO (quando tiver) junto com a responsta

        var pedidosFila = filaUseCasePort.obterPedidosNaFila(paginacao.getPageNumber(), paginacao.getPageSize());
        var pedidosFilaDTO = pedidosFila.stream().map(obj -> new PedidoFilaDTO().from(obj)).collect(Collectors.toList());
        return new PageImpl<>(pedidosFilaDTO);
    }

    @DeleteMapping("/fila/{numero}")
    public ResponseEntity<?> removerPedidoDaFila(@PathVariable Long numero) {
        try {
            filaUseCasePort.removerPedidoDaFila(numero);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

}
