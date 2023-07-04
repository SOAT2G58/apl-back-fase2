package aplbackfase1.application.web;

import aplbackfase1.application.web.requests.PedidoRequest;
import aplbackfase1.application.web.responses.PedidoDTO;
import aplbackfase1.domain.enums.StatusPedido;
import aplbackfase1.domain.model.Pedido;
import aplbackfase1.domain.model.PedidoProduto;
import aplbackfase1.domain.ports.in.IPedidoUseCasePort;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tech-challenge")
@RequiredArgsConstructor
public class PedidoControllerAdapter {

    private final IPedidoUseCasePort pedidoUseCasePort;

    @PostMapping("/pedido")
    public ResponseEntity<?> criarPedido(@RequestBody @NotNull PedidoRequest request) {
        Pedido pedido = this.pedidoUseCasePort.cadastrar(request.from(request));
        return new ResponseEntity<>(new PedidoDTO().from(pedido), HttpStatus.CREATED);
    }

    @PostMapping("/pedido-incluir-produto")
    public ResponseEntity<Pedido> adicionarProduto(@RequestBody PedidoProduto produto) {
        return new ResponseEntity<>(pedidoUseCasePort.adicionarProduto(produto), HttpStatus.OK);
    }

    @GetMapping("/pedido")
    public ResponseEntity<List<Pedido>> buscarTodos(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "100") int pageSize) {
        return new ResponseEntity<>(pedidoUseCasePort.buscarTodos(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/pedido/id/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable UUID id) {
        return pedidoUseCasePort.buscarPorId(id)
                .map(pedido -> new ResponseEntity<>(pedido, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/pedido/cliente/{idCliente}")
    public ResponseEntity<List<Pedido>> buscarPedidosPorCliente(@PathVariable UUID idCliente) {
        return new ResponseEntity<>(pedidoUseCasePort.buscarPedidosPorCliente(idCliente), HttpStatus.OK);
    }

    @GetMapping("/pedido/status/{statusPedido}")
    public ResponseEntity<List<Pedido>> buscarPedidosPorStatus(@PathVariable StatusPedido statusPedido) {
        return new ResponseEntity<>(pedidoUseCasePort.buscarPedidosPorStatus(statusPedido), HttpStatus.OK);
    }

    @GetMapping("/pedido/cliente/{idCliente}/status/{statusPedido}")
    public ResponseEntity<List<Pedido>> buscarPedidosPorClienteEStatus(@PathVariable UUID idCliente, @PathVariable StatusPedido statusPedido) {
        return new ResponseEntity<>(pedidoUseCasePort.buscarPedidosPorClienteEStatus(idCliente, statusPedido), HttpStatus.OK);
    }

    @PutMapping("/pedido")
    public ResponseEntity<Pedido> atualizar(@RequestBody Pedido pedido) {
        return new ResponseEntity<>(pedidoUseCasePort.atualizar(pedido), HttpStatus.OK);
    }

    @DeleteMapping("/pedido/{id}")
    public ResponseEntity<Void> remover(@PathVariable UUID id) {
        pedidoUseCasePort.remover(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/checkout/{id}")
    public ResponseEntity<Pedido> checkout(@PathVariable UUID id) {
        return new ResponseEntity<>(pedidoUseCasePort.checkout(id), HttpStatus.OK);
    }
}
